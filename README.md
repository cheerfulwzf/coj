## Cheerful Online Judge系统

### 介绍
一个支持分布式、可扩展性的online judge系统，支持各种cpp、python、go、js等大部分主流语言的代码评测，也可以在配置文件中自定义添加语言。

~~~yaml
#配置其编译命令和运行命令即可
#自定义语言请严格按照以下方式
custom-cmd:
  map:
    11:
      - python2 # 语言名称
      - python2 -m py_compile PATH/main.py #编译
      - python2 PATH/main.py #运行
    12:
      - node.js # 名称
      - judge.js.runCmd=node PATH/main.js #编译和运行
#    12:
#      - python3
#      - python3 -m py_compile PATH/main.py
#      - python3 -m py_compile PATH/main.py
~~~


系统分为题目、竞赛、社区、判题机等多个服务模块，各个服务模块因为注册中心的存在可以根据你的用户量需求随时添加或减少其实例数量。

前后端分离，你可以通过后端暴露的接口文档来自己搭建前端。

### 状态：正在开发中

我的毕业设计，SICAU 2019级计算机在读

本项目是一个基于SpringBoot的微服务架构，其中使用了一些消息中间件、缓存、分布式服务的中间件来协助开发，后续会计划打包成docker镜像。

运行环境：CentOS 7.6

### 用户层架构


### 判题机架构



若有不对的地方，还请大家指出。

若需要帮助，请联系我cheerful0120@qq.com。

主要问题：



