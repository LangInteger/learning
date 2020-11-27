# 使用 Visual Studio Code 开发 Java 程序

本文主要介绍如何使用 Visual Studio Code 来进行 Java 项目日常开发。

## 1 必备插件

安装后打开 Java 项目即可拥有 IDE 的核心功能（代码高亮、自动补全、引用查找、转到定义等）

- Language Support for Java(TM) by Red Hat
- Lombok Annotations Support for VS Codium

## 2 常用操作

WARNING：某些命令在中文输入法下无法正常工作

### 2.1 编辑器常用操作

- 文件搜索：
  - OS X: command + p
  - Linux: ctrl + p
- 召唤/关闭 Terminal
  - ctrl + `
- 打开新 Terminal
  - ctrl + shift + `
- 召唤/关闭左侧面板(必须先关闭 Terminal)
  - OS X: command + b
  - Linux: ctrl + b
- 光标在编辑区和左侧面板间切换
  - OS X: command + shift + e
  - Linux: ctrl + shift + e
- 左侧面板打开、关闭文件夹
  - enter
- 关闭当前打开的文件
  - OS X: command + w
  - Linux: ctrl + w
- 反复横跳（在上一个浏览处和下一个浏览处之间切换
  - OS X: command + alt + 左箭头/右箭头
- 查找引用/转到定义
  - OS X: command + b

### 2.2 Gradle 日常操作

使用 gradle 命令来完成原本在 IDE 里面完成的操作

- 执行完整的装配、检查、测试、打包: gradle clean build
- 执行测试: gradle clean test integrationTest
- 执行 pmdMain 检查：gradle clean pmdMain
- 执行某些单元测试（支持通配符）: gradle test --tests xxx
- 执行某些集成测试: gradle integrationTest --tests xxx
- 在本地启动服务: gradle bootRun

## 3 疑难杂症

### 3.1 莫名其妙的找不到引入类的问题

解决方案:

- rm -rf $HOME/Library/Application\ Support/Code/User/workspaceStorage/*

参考:

- [Visual Studio Code - Java - Import Errors and More](https://stackoverflow.com/questions/45743779/visual-studio-code-java-import-errors-and-more)
