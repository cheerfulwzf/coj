FROM openjdk:8-jdk-alpine
# 定义了一个持久化存储，指向容器中的tmp文件夹。SpringBoot应用为内置的Tomcat服务器实例创建的默认工作目录为tmp，通过该命令，可以在运行Docker的[宿主机]目录/var/lib/docker创建一个临时的目录，挂接到容器内部的tmp
VOLUME /tmp
# 声明运行时容器提供服务端口，这只是一个声明，在运行时并不会因为这个声明应用就会开启这个端口的服务。命令行的expose可以起作用
EXPOSE 6088
# 把本地目录下target文件夹里打好的jar文件添加到容器里，重命名为helloapp.jar.
ADD target/*.jar oj-judge.jar
#ENV JAVA_OPTS="-Dserver.port=88"
# 容器镜像运行的起始点
ENTRYPOINT exec java -jar /oj-judge.jar