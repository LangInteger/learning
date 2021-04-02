# Git WorkFlow

- 推荐 Git/Github 教程：[廖雪峰-Git教程](https://www.liaoxuefeng.com/wiki/896043488029600)

下面介绍一些常见 Git 工作流。

## 1 拉取一个全新仓库改动并推送变更全过程

1. 克隆仓库：git clone git@github.com:Insight-Group/MFIN7036.git
2. 跳转目录：cd MFIN7036
3. 在各种软件中打开此文件夹，编辑、新增文件夹内的文件
4. 查看git仓库状态：git status
5. 提交所有更改到暂存区：git add .
6. 确认所有更改：git commit -m "干了个大事儿"
7. 推送到远程：git push

## 2 在本地已有仓库改动并推送变更全过程

1. 跳转目录：cd your/path/to/git/repo/MFIN7036
2. 在各种软件中打开此文件夹，编辑、新增文件夹内的文件
3. 查看git仓库状态：git status
4. 提交所有更改到暂存区：git add .
5. 确认所有更改：git commit -m "干了个大事儿"
6. 推送到远程：git push
- PS. 在第2步之前拉取远程分支内容到本地(可能别人改了推送了)：git pull。不然可能出现 git push失败的情况。

## 3 与远程仓库内容冲突 git push 失败的解决办法
1. git reset --soft HEAD^ 回退一个commit，相关文件的改动保留(如果本地有多个commit，则增加^符号数量一次回退多个commit)
2. git add . 添加所有变更文件到暂存区
3. git stash 将所有文件改动存放到一个【临时区】
4. git pull 拉取远程最新commit
5. git stash pop 将【临时区】改动释放出来
6. git add . 添加所有变更文件到暂存区
7. git commit -m "又干了个大事儿"
8. git push

## 4 其他常见命令速查

- 查看分支：git branch -a
- 切换分支：git checkout xxxx (xxx必须已经存在，如main、gh-pages)
- 切换并新建分支：git checkout -b xxxx (xxxx分支必须不存在)
- 删除分支：git branch -d xxx
- 强制删除分支((忽略该分支可能存在的的未合并到主分支的内容)：git branch -D xxx
- 同步远端分支：git fetch -p
- 查看历史记录：git log 或者 git log --oneline
