# 使用 Visual Studio Code 开发 Java 程序

本文主要介绍如何使用 Visual Studio Code 来进行 Java 项目日常开发。

## 0 待解决

- ctrl + b 有时候加载引用缓慢
- groovy 支持不好，用 groovy 编写的测试代码无法正常跳转引用等

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
  - Linux: ctrl + alt + -/+
- 查找引用/转到定义
  - OS X: command + b
  - Linux: F12
- 重新加载 Window
  - OS X:
    - command + shift + P (open command palette)
    - reload window

### 2.2 Gradle 常用操作

使用 gradle 命令来完成原本在 IDE 里面完成的操作

- 执行完整的装配、检查、测试、打包: gradle clean build
- 执行测试: gradle clean test integrationTest
- 执行 pmdMain 检查：gradle clean pmdMain
- 执行某些单元测试（支持通配符）: gradle test --tests xxx
- 执行某些集成测试（支持通配符）: gradle integrationTest --tests xxx
- 在本地启动服务: gradle bootRun

## 3 进阶需求

### 3.1 编辑器进阶操作

搭配 VIM 的编辑器常用操作

- 进入命令模式：英文冒号
- 设置展示行号：命令模式下 set number
- 设置展示相对行号: 命令模式下 set relativenumber
- 移动
  - 基本移动: jlik
  - 移动一个单词: w
  - 移动到文档开头: gg
  - 移动到文档结尾: G
  - 向上移动 5 行: 5j
  - 移动到屏幕顶端: H
  - 移动到屏幕中部: M
  - 移动到屏幕底部: L
  - 向下半页: ctrl + f
  - 向上半页: ctrl + b
  - 将光标所在处置为屏幕中部: zz
  - 将光标移动到当前行的第一个 x 字符: fx
  - 将光标移动到当前行的第 3 个 x 字符: 3fx

### 3.2 代码格式化

配置：

- 打开命令窗口：command + shift + p
- 执行指定格式配置文件位置命令：Java:Open Java formatter settings
- 指定格式配置文件位置：/Users/annoyingbanana/IdeaProjects/cntehang/quality_assurance/config/checkstyle/checkstyle.xml

使用：

- 执行代码格式化操作：shift + alt + f
- 保存文件时自动执行格式化操作：
  - 设置
  - 搜索 format
  - 勾选：Editor: Format on Save

### 3.3 管理文件包引用

- 整理当前文件的引用，清除无用引用：shift + alt + o
- 保存文件时自动执行引用清理操作：
  - 设置
  - 搜索 import
  - 勾选 Java > Save Actions: Organize Imports

## 4 疑难杂症

### 4.1 莫名其妙的找不到引入类的问题

解决方案:

- rm -rf $HOME/Library/Application\ Support/Code/User/workspaceStorage/\*

参考:

- [Visual Studio Code - Java - Import Errors and More](https://stackoverflow.com/questions/45743779/visual-studio-code-java-import-errors-and-more)
