# Nginx 快速搭建与基本参数的使用

## 1 快速安装

- [Nginx 官网 doc](http://nginx.org/en/linux_packages.html)

## 2 基本参数使用

### 2.1 安装目录

安装的 rpm 包，使用命令

- rpm -ql nginx

就可以列出安装的服务对应的配置文件、新建的目录等

```text
/etc/logrotate.d/nginx
/etc/nginx
/etc/nginx/conf.d
/etc/nginx/conf.d/default.conf
/etc/nginx/fastcgi_params
/etc/nginx/koi-utf
/etc/nginx/koi-win
/etc/nginx/mime.types
/etc/nginx/modules
/etc/nginx/nginx.conf
/etc/nginx/scgi_params
/etc/nginx/uwsgi_params
/etc/nginx/win-utf
/etc/sysconfig/nginx
/etc/sysconfig/nginx-debug
/usr/lib/systemd/system/nginx-debug.service
/usr/lib/systemd/system/nginx.service
/usr/lib64/nginx
/usr/lib64/nginx/modules
/usr/libexec/initscripts/legacy-actions/nginx
/usr/libexec/initscripts/legacy-actions/nginx/check-reload
/usr/libexec/initscripts/legacy-actions/nginx/upgrade
/usr/sbin/nginx
/usr/sbin/nginx-debug
/usr/share/doc/nginx-1.16.1
/usr/share/doc/nginx-1.16.1/COPYRIGHT
/usr/share/man/man8/nginx.8.gz
/usr/share/nginx
/usr/share/nginx/html
/usr/share/nginx/html/50x.html
/usr/share/nginx/html/index.html
/var/cache/nginx
/var/log/nginx
```

- etc 放核心配置

### 2.2 编译参数

### 2.3 Nginx 基本配置语法

## 3 常用命令

- 查看版本：nginx -v
- 查看编译参数：nginx -V