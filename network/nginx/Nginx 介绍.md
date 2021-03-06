# Nginx 介绍

## 1 Nginx 的中间件架构

网站后台往往有很多应用服务，应用之间的直接调用或与操作系统之间的直接交互会导致代码耦合性高，需要一个代理来负责请求，应用只负责自己的内部逻辑。

中间件不仅可以用于代理请求，还可以用于负载均衡、缓存、安全防控等设置。

### 1.1 什么是 Nginx

Nginx 是一个开源且高性能、可靠的 HTTP 中间件、代理服务。

### 1.2 常见的 HTTP 服务

与 Nginx 类似的

- HTTPD - Apache 基金会
- IIS - 微软
- GWS - Google

## 2 Why Nginx

### 2.1 IO 多路复用 epoll

IO 复用是指一个 socket 完成整个 IO 流的请求

- 多线程的方式
- 巴拉巴拉没讲清楚

同一个线程并发交替完成

- select 模型
  - 能够监视文件描述符的个数有限
- epoll 模型
  - 采用系统的回调函数之间将 FD 放入，效率更高

### 2.2 轻量级

- 功能模块少
- 代码模块化

### 2.3 CPU 亲和(affinity)

为什么需要 CPU 亲和

- 分配 woker 到不同的 cpu 核心上，避免过多的 cpu 调度切换
- 就是把 CPU 的核心和 Nginx 的工作进程绑定的方式，减少切换 cpu，获取更改性能

### 2.4 sendfile

静态文件需要很少的用户空间的逻辑处理，Nginx 引入了零拷贝的模式，文件拷贝只通过内核空间就返回给用户。
