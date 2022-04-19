#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/user.h>
#include <sys/ptrace.h>
#include <sys/time.h>
#include <sys/resource.h>
#include <sys/wait.h>

#define AC 0
#define PE 1
#define TLE 2
#define MLE 3
#define WA 4
#define RE 5
#define OLE 6
#define CE 7
#define SE 8

struct result {
    int status;
    int timeUsed;
    int memoryUsed;
};

/**
 * set the process limit(cpu and memory)
 * @param time_limit 时间限制
 * @param memory_limit 内存限制
 */
void setProcessLimit(int time_limit, int memory_limit) {
    struct rlimit rl;
//    RLIMIT_CPU 最大允许的CPU使用时间(second)
    rl.rlim_cur = time_limit / 1000;
    rl.rlim_max = rl.rlim_cur + 1;
    setrlimit(RLIMIT_CPU, &rl);


//    RLIMIT_DATA //进程数据段的最大值。(b)
    rl.rlim_cur = memory_limit * 1024;
    rl.rlim_max = rl.rlim_cur + 1024;
    setrlimit(RLIMIT_DATA, &rl);
}

/**
 * run the user process
 * @param args
 * @param time_limit 时间限制
 * @param memoryLimit 内存限制
 * @param in 输入文件
 * @param out 输出文件
 */
void runCmd(char *args[],long time_limit, long memoryLimit, char *in, char *out) {
    /*
     * O_RDWR 只写模式、O_CREAT：如果文件不存在则创建
     * 0644-->用户具有读写权限
     */
    int newstdin = open(in,O_RDWR|O_CREAT,0644);
    int newstdout = open(out,O_RDWR|O_CREAT,0644);
    setProcessLimit(time_limit, memoryLimit);
    if (newstdout != -1 && newstdin != -1){
        dup2(newstdout,fileno(stdout));
        dup2(newstdin,fileno(stdin));
        if (execvp(args[0],args) == -1) {
            printf("====== Failed to start the process! =====\n");
        }
        close(newstdin);
        close(newstdout);
    } else {
        printf("====== Failed to open file! =====\n");
    }
}

/**
 * get data of the user process
 * @param ru 进程资源信息
 * @param data 时间使用和内存使用
 */
void getResult(struct rusage *ru, int data[2]) {
//    struct rusage {
//        struct timeval ru_utime; /* user time used 用户态使用的时间 */
//        struct timeval ru_stime; /* system time used 内核态使用的时间 */
//        long   ru_maxrss;       //最大内存消耗
//    };
    data[0] = ru->ru_utime.tv_sec * 1000
            + ru->ru_utime.tv_usec / 1000
            + ru->ru_stime.tv_sec * 1000
            + ru->ru_stime.tv_usec / 1000;
    data[1] = ru->ru_maxrss;
}

/**
 * monitor the user process
 * @param pid 子进程id
 * @param time_limit 时间限制
 * @param memoryLimit 空间限制
 * @param rest {@link result}
 */
void monitor(pid_t pid, int time_limit, int memoryLimit, struct result *rest) {
    int status;
    struct rusage ru;
//  wait4()回收子进程
    if (wait4(pid, &status, 0, &ru) == -1)
        printf("wait4 failure");
    rest->timeUsed = ru.ru_utime.tv_sec * 1000
            + ru.ru_utime.tv_usec / 1000
            + ru.ru_stime.tv_sec * 1000
            + ru.ru_stime.tv_usec / 1000;
    rest->memoryUsed = ru.ru_maxrss;
//  导致子进程异常终止的信号的数量，判别是否正常退出
    if (WIFSIGNALED(status)) {
        switch (WTERMSIG(status)) {
            case SIGSEGV:
                if (rest->memoryUsed > memoryLimit)
                    rest->status = MLE;
                else
                    rest->status = RE;
                break;
            case SIGALRM:
            case SIGXCPU:
                rest->status = TLE;
                break;
            default:
                rest->status = RE;
                break;
        }
    } else {
        if (rest->timeUsed > time_limit)
            rest->status = TLE;
        else if (rest->memoryUsed > memoryLimit)
            rest->status = MLE;
        else
            rest->status = AC;
    }
}


int run(char *args[],int time_limit, int memoryLimit, char *in, char *out){
    //fork子进程，保护进程中其他数据，父进程来控制他的启动推出等
    pid_t pid = vfork();
    if(pid<0)
        printf("error in fork!\n");
    else if(pid == 0) {
        runCmd(args, time_limit, memoryLimit, in, out);
    } else {
        //父进程监控子进程状态
        struct result rest;
        monitor(pid, time_limit, memoryLimit, &rest);
        printf("{\"status\":%d,\"timeUsed\":%d,\"memoryUsed\":%d}", rest.status, rest.timeUsed, rest.memoryUsed);
    }
}
/**
 *分割字符串命令
 *@del @
 */
void split( char **arr, char *str, const char *del){
    char *s =NULL;
    s = strtok(str,del);
    while(s != NULL)
    {
        *arr++ = s;
        s = strtok(NULL,del);
    }
    *arr++ = NULL;
}

int main(int argc,char *argv[])
{
    char *cmd[20];
    /**
     * cmd eg:
     * /judge java@-classpath@/tmp/OnlineJudgeWorkspace/test@Main
     * 1024
     * 65535
     * /tmp/OnlineJudgeWorkspace/test/1.in
     * /tmp/OnlineJudgeWorkspace/test/temp.out
     */
    split(cmd, argv[1], "@");
    run(cmd, atoi(argv[2]), atoi(argv[3]), argv[4], argv[5]);
    return 0;
}