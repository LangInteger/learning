# Docker 常用命令

## 1 常用知识

### 1.1 docker run

- docker run -d: Run container in background and print container ID
- docker run -i: Keep STDIN open even if not attached
- docker run -t: Allocate a pseudo-TTY
- docker run -e: set environment variables

### 1.2 ENTRYPOINT & CMD

Both `CMD` and `ENTRYPOINT` instructions define what command gets executed when running a container. There are few rules that describe their co-operation.

- Dockerfile should specify at least one of `CMD` or `ENTRYPOINT` commands
- `ENTRYPOINT` should be defined when using the container as an executable
- `CMD` should be sued as a way of defining default arguments for an `ENTRYPOINT` command or for executing ad ad-hoc command in a container
- `CMD` will be overriden when running the container with alternative arguments

From [docker reference - entrypoint](https://docs.docker.com/engine/reference/builder/#entrypoint):

- Command line arguments to `docker run <image>` will be appended after all elements in an exec form `ENTRYPOINT`, and will override all elements specified using `CMD`.
- Using the exec form of `ENTRYPOINT` to set fairly stable default commands and arguments and then use either form of `CMD` to set additional defaults that are more likely to be changed.

## 2 常见问题

### 2.1 `docker run -it ubuntu /bin/bash` vs `docker run -it ubuntu'

- [Reference](https://askubuntu.com/questions/938869/docker-run-ubuntu-bin-bash-vs-docker-run-ubuntu)
- `docker run -it ubuntu /bin/bash` 后面的 `/bin/bash` 是用来指定运行命令的解释器，会覆盖 ubuntu 的 Dockerfile 中的 `CMD` 部分中指定的参数，但是恰好 ubuntu 的 Dockerfile 中的 `CMD` 部分中指定的参数也为 `/bin/bash`，即 bash 即为其缺省参数。但是在一些数据库引擎的 docker 镜像中却不是这样，需要明确指定 `/bin/bash`。

更多资料：

- [SO-Thread: Why do you need to put #!/bin/bash at the beginning of a script file?](https://stackoverflow.com/questions/8967902/why-do-you-need-to-put-bin-bash-at-the-beginning-of-a-script-file)

### 2.2 跑一个 mariadb

- docker pull mariadb
- docker run --name my-mariadb -e MYSQL_ROOT_PASSWORD=root -idt -p 3306:3306 mariadb:latest
- docker exec -it my-mariadb  bash
- docker stop my-mariadb

### 2.3 使用新的命令参数启动停止的 docker container

- [SO-Thread: How to start a stopped Docker container with a different command?
](https://stackoverflow.com/questions/32353055/how-to-start-a-stopped-docker-container-with-a-different-command)



## 3. 参考资料

- [Dockerfile reference](https://docs.docker.com/engine/reference/builder/#entrypoint)
  - 对 Dockerfile 中的一切参数进行了说明
