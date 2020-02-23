# Nginx 安装

## 1 启动及文件映射

[参考](https://www.ruanyifeng.com/blog/2018/02/nginx-docker.html)

```shell
$ docker container run \
  -d \
  -p 127.0.0.1:8080:80 \
  --rm \
  --name lang-nginx \
  --volume "$PWD/html":/usr/share/nginx/html \
  --volume "$PWD/conf":/etc/nginx  \
  nginx
```

## 2 配置文件相关

配置文件为 `/etc/nginx/nginx.conf`，映射出来之后，会在映射目录查找相应文件。

- 测试配置文件是否正确：docker exec -it lang-nginx nginx -t
- 重新加载配置：docker exec -it lang-nginx nginx -s reload
