package com.cheerful.oj.judge.factory.base;

import com.alibaba.fastjson.JSON;
import com.cheerful.oj.common.constant.JudgeStatusConstant;
import com.cheerful.oj.common.dto.JudgeResultDTO;
import com.cheerful.oj.common.dto.JudgeTaskDTO;
import com.cheerful.oj.common.dto.ResultCaseDTO;
import com.cheerful.oj.judge.util.ExecutorUtil;
import com.cheerful.oj.judge.util.FileUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/10 21:45
 * @DESCRIPTION:
 */
public abstract class JudgeHandler {

  @Value("${judge.judgePath}")
  private String judgePath;

  @Value("${judge.scriptPath}")
  private String scriptPath;

  /**
   * 判题任务的主流程
   *
   * @param task 判题任务
   * @return
   */
  public JudgeResultDTO judge(JudgeTaskDTO task) {
    JudgeResultDTO result = new JudgeResultDTO();
    // TODO: 2022/3/10 检查判题任务类的数据完整--->消息的生产方已解决

    //创建工作目录 /tmp/OnlineJudgeWorkspace/time
    File path = new File(judgePath + File.separator + System.currentTimeMillis());
    if (!createWorkspace(task, result, path)) {
      ExecutorUtil.exec("rm -rf " + path.getPath(), 1000);
      return result;
    }

    //编译
    if (!compiler(result, path)) {
      ExecutorUtil.exec("rm -rf " + path.getPath(), 1000);
      return result;
    }

    //编译没问题，终于开始执行代码判断输入输出了
    runSource(task, result, path);
    ExecutorUtil.exec("rm -rf " + path.getPath(), 1000);
    return result;
  }

  /**
   * 编译程序
   *
   * @param result
   * @param path
   * @return
   */
  private boolean compiler(JudgeResultDTO result, File path) {
    ExecutorUtil.ExecMessage msg = handlerCompiler(path);
    if (msg.getError() != null) {
      result.setGlobalMsg(msg.getError());
      return false;
    }
    return true;
  }

  /**
   * 编译顺利完成，运行代码，判断结果
   *
   * @param task   判题任务
   * @param result 返回的结果集
   * @param path   判题源代码文件
   */
  private void runSource(JudgeTaskDTO task, JudgeResultDTO result, File path) {
    //cmd : command timeLimit memoryLimit inFile tmpFile
    //eg: /judge java@-classpath@/tmp/OnlineJudgeWorkspace/test@Main 1024 65535 /tmp/OnlineJudgeWorkspace/test/1.in /tmp/OnlineJudgeWorkspace/test/temp.out
    String cmd = "script process timeLimit memoryLimit inputFile tmpFile";
    cmd = cmd.replace("script", scriptPath);
    cmd = cmd.replace("process", getRunCommand(path).replace(" ", "@"));
    cmd = cmd.replace("timeLimit", task.getTimeLimit().toString());
    cmd = cmd.replace("memoryLimit", task.getMemoryLimit().toString());
    cmd = cmd.replace("tmpFile", path.getPath() + File.separator + "tmp.out");
    List<ResultCaseDTO> cases = new ArrayList<>();
    for (int i = 0; ; i++) {
      File inFile = new File(path.getPath() + File.separator + i + ".in");
      File outFile = new File(path.getPath() + File.separator + i + ".out");
      if (!inFile.exists() || !outFile.exists()) {
        break;
      }
      System.out.println(cmd.replace("inputFile", inFile.getPath()));
      ExecutorUtil.ExecMessage msg = ExecutorUtil.exec(cmd.replace("inputFile", inFile.getPath()),
        50000);
      ResultCaseDTO itemCase = JSON.parseObject(msg.getStdout(), ResultCaseDTO.class);
      if (itemCase == null) {
        itemCase = new ResultCaseDTO(JudgeStatusConstant.SE.getCode(), -1, -1, null);
      }
      if (itemCase.getStatus().equals(JudgeStatusConstant.AC.getCode())) {
        String input = FileUtil.read(inFile);
        itemCase.setInput(input);
        //c语言返回AC只代表输出成功，我们还要判断结果真的是否正确
        diff(itemCase, new File(path.getPath() + File.separator + "tmp.out"), outFile);
      }
      //运行报错
      if (msg.getError() != null) {
        itemCase.setStatus(JudgeStatusConstant.RE.getCode());
        itemCase.setMemoryUsed(-1);
        itemCase.setTimeUsed(-1);
        itemCase.setErrorMessage(msg.getError());
      }
      cases.add(itemCase);
      ExecutorUtil.exec("rm " + path.getPath() + File.separator + "tmp.out", 1000);
    }
    result.setResult(cases);
  }

  /**
   * 比对结果是否正确
   *
   * @param itemCase 每次输入输出
   * @param tempOut  用户代码输出结果文件
   * @param outFile  正确的输出结果文件
   */
  private void diff(ResultCaseDTO itemCase, File tempOut, File outFile) {
    String tem = FileUtil.read(tempOut);
    String std = FileUtil.read(outFile);
    itemCase.setTempOut(tem);
    itemCase.setOutput(std);
    //判断输入输出是否一致
    if (tem.equals(std)) {
      itemCase.setStatus(JudgeStatusConstant.AC.getCode());
      return;
    }
    StringBuilder s1 = new StringBuilder();
    StringBuilder s2 = new StringBuilder();
    for (int i = 0; i < tem.length(); i++) {
      if (tem.charAt(i) != ' ' && tem.charAt(i) != '\n') {
        s1.append(tem.charAt(i));
      }
    }
    for (int i = 0; i < std.length(); i++) {
      if (std.charAt(i) != ' ' && std.charAt(i) != '\n') {
        s2.append(std.charAt(i));
      }
    }
    //输入输出一致还是直接WA
    if (s1.toString().equals(s2.toString())) {
      itemCase.setStatus(JudgeStatusConstant.PE.getCode());
    } else {
      itemCase.setStatus(JudgeStatusConstant.WA.getCode());
    }
  }

  /**
   * 是否能成功创建工作目录
   *
   * @param task   判题任务
   * @param result 结果集
   * @param path   文件路径
   * @return true or false
   */
  private boolean createWorkspace(JudgeTaskDTO task, JudgeResultDTO result, File path) {
    try {
      if (!path.exists()) {
        path.mkdirs();
      }
      //创建输入输出文件
      if (task.getQid() != null) {
        for (int i = 0; i < task.getInput().size(); i++) {
          File inFile = new File(path, i + ".in");
          File outFile = new File(path, i + ".out");
          inFile.createNewFile();
          FileUtil.write(task.getInput().get(i), inFile);
          outFile.createNewFile();
          FileUtil.write(task.getOutput().get(i), outFile);
        }
      }
      // TODO: 2022/3/11 后期新增把测试数据改成下载文件的方式
      //创建源代码文件
      createSrc(task, path);
    } catch (IOException e) {
      result.setGlobalMsg("服务器路径出错" + e);
      return false;
    }
    return true;
  }


  /**
   * 根据提交的代码创建对应的源程序（模板方法）
   *
   * @param task 判题任务
   * @param path 创建源程序文件
   * @throws IOException
   */
  protected abstract void createSrc(JudgeTaskDTO task, File path) throws IOException;

  /**
   * 各种语言编译（模板方法）
   *
   * @param path 编译文件的路径
   * @return
   */
  protected abstract ExecutorUtil.ExecMessage handlerCompiler(File path);

  /**
   * 各语言运行（模板方法）
   *
   * @param path 运行文件的路径
   * @return eg:java -classpath PATH Main
   */
  protected abstract String getRunCommand(File path);
}
