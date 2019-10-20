# Nginx 快速搭建与基本参数的使用

## 1 快速安装

- [Nginx 官网 doc](http://nginx.org/en/linux_packages.html)

## 2 基本参数使用

### 2.1 安装目录

安装的 rpm 包，使用命令

- rpm -ql nginx

就可以列出安装的服务对应的配置文件、新建的目录等

```text
/etc/logrotate.d/nginx Nginx 日志轮转，用于 logrotate 服务的日志切割
/etc/nginx 目录、配置文件
/etc/nginx/nginx.conf Nginx 主配置文件
/etc/nginx/conf.d
/etc/nginx/conf.d/default.conf 默认配置文件，刚安装时候的配置文件
/etc/nginx/fastcgi_params cgi 配置相关
/etc/nginx/scgi_params cgi 配置相关
/etc/nginx/uwsgi_params cgi 配置相关
/etc/nginx/koi-utf nginx 编码转换映射文件
/etc/nginx/koi-win nginx 编码转换映射文件
/etc/nginx/win-utf nginx 编码转换映射文件
/etc/nginx/mime.types 设置 http 协议的 Content-Type 与扩展名对应关系
/etc/sysconfig/nginx 用于配置出系统守护进程管理器管理方式
/etc/sysconfig/nginx-debug
/usr/lib/systemd/system/nginx-debug.service
/usr/lib/systemd/system/nginx.service

/etc/nginx/modules Nginx 模块目录
/usr/lib64/nginx
/usr/lib64/nginx/modules

/usr/sbin/nginx Nginx 服务的启动管理的终端命令
/usr/sbin/nginx-debug

/usr/share/doc/nginx-1.16.1 Nginx 的手册和帮助文件
/usr/share/doc/nginx-1.16.1/COPYRIGHT
/usr/share/man/man8/nginx.8.gz

/var/cache/nginx Nginx 的缓存目录

/var/log/nginx Nginx 的日志目录

/usr/libexec/initscripts/legacy-actions/nginx
/usr/libexec/initscripts/legacy-actions/nginx/check-reload
/usr/libexec/initscripts/legacy-actions/nginx/upgrade

/usr/share/nginx
/usr/share/nginx/html
/usr/share/nginx/html/50x.html
/usr/share/nginx/html/index.html
```

- etc 放核心配置

### 2.2 编译参数

```text
nginx version: nginx/1.16.1
built by gcc 4.8.5 20150623 (Red Hat 4.8.5-36) (GCC)
built with OpenSSL 1.0.2k-fips  26 Jan 2017
TLS SNI support enabled
configure arguments: --prefix=/etc/nginx --sbin-path=/usr/sbin/nginx --modules-path=/usr/lib64/nginx/modules --conf-path=/etc/nginx/nginx.conf --error-log-path=/var/log/nginx/error.log --http-log-path=/var/log/nginx/access.log --pid-path=/var/run/nginx.pid --lock-path=/var/run/nginx.lock --http-client-body-temp-path=/var/cache/nginx/client_temp --http-proxy-temp-path=/var/cache/nginx/proxy_temp --http-fastcgi-temp-path=/var/cache/nginx/fastcgi_temp --http-uwsgi-temp-path=/var/cache/nginx/uwsgi_temp --http-scgi-temp-path=/var/cache/nginx/scgi_temp --user=nginx --group=nginx --with-compat --with-file-aio --with-threads --with-http_addition_module --with-http_auth_request_module --with-http_dav_module --with-http_flv_module --with-http_gunzip_module --with-http_gzip_static_module --with-http_mp4_module --with-http_random_index_module --with-http_realip_module --with-http_secure_link_module --with-http_slice_module --with-http_ssl_module --with-http_stub_status_module --with-http_sub_module --with-http_v2_module --with-mail --with-mail_ssl_module --with-stream --with-stream_realip_module --with-stream_ssl_module --with-stream_ssl_preread_module --with-cc-opt='-O2 -g -pipe -Wall -Wp,-D_FORTIFY_SOURCE=2 -fexceptions -fstack-protector-strong --param=ssp-buffer-size=4 -grecord-gcc-switches -m64 -mtune=generic -fPIC' --with-ld-opt='-Wl,-z,relro -Wl,-z,now -pie'

```

- nginx -V

### 2.3 Nginx 基本配置语法

## 3 常用命令

- 查看版本：nginx -v
- 查看编译参数：nginx -V