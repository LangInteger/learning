---
presentation:
  # width: 1200
  # height: 600
  theme: solarized.css
  enableSpeakerNotes: true
---

<!-- slide data-notes="Write your note here" -->

# Git in Action

### 2022-06-04
### 刘 郎

<!-- slide -->

## What‘s git
![](how-to-use-git.gif)

<!-- slide vertical=true  -->

- Staging area 是什么
- Tracked & Untracked Modified & Unmodified
- 切换分支为什么很快
- git reflog 工作原理，如果 reflog 数据也没了呢
- git 每次 commit 存储的是增量还是全量数据

<!-- slide vertical=true  -->

[Pro Git](https://git-scm.com/book/en/v2)

![](./progit2.png)

<!-- slide vertical=true  -->

Again, what is git

- 白话：Git is a free and open source distributed version control system designed to handle everything from small to very large projects with speed and efficiency
- 黑话：Git is a content-addressable filesystem

<!-- slide vertical=true -->

Subcommands

- Porcelain commands: add, commit, checkout, branch ...
  - a full user-friendly VCS
- Plumbing commands:  cat-file, hash-object, update-index, write-tree, commit-tree ...
  - a toolkit for a version control system
  - designed to be chained together UNIX-style or called from scripts

<!-- slide -->

## Today's work

```shell
echo "test content 1" > test1.txt
echo "test content 2" > test2.txt
git add .
git commit -m "first commit"

echo "test content 1 modified" > test1.txt
git add .
git commit -m "second commit"
```

<!-- slide vertical=true -->

#### cheetsheet 1

data storage: k-v `find .git/objects -type f`
value=compress(header(type+contentLen)+content)
key=sha1(value)

data type in git

|   Seq   | Type | How to create |
| ---|:-------------:| -----:|
| 1 | blob | git hash-object |
| 2 | tree | git update-index <br /> git write-tree |
| 3 | commit | git commit-tree |

<!-- slide vertical=true -->

#### cheetsheet 2

command mapping

|   Seq   | Porcelain | Plumbing |
| ---|:-------------:| -----:|
| 1  | git add | git hash-object<br />git update-index|
| 2 | git commit | git write-tree<br />git commit-tree |

<!-- slide vertical=true -->

#### cheetsheet 3

show data type: `git cat-file -t hashxx`

show data content: `git cat-file -p hashxx`

<!-- slide vertical=true -->

implement with plumbing commands

```shell
echo "test content 1" > test1.txt
echo "test content 2" > test2.txt
git hash-object -w test1.txt # hash1 blob
git hash-object -w test2.txt # hash2 blob
git update-index --add --cacheinfo 100644 hash1 test1.txt
git update-index --add --cacheinfo 100644 hash2 test2.txt
git write-tree # hash3 tree
echo 'First commit' | git commit-tree hash3 # hash4 commit

echo "test content 1 modified" > test1.txt
git hash-object -w test1.txt # hash5 blob
git update-index --add --cacheinfo 100644 hash5 test1.txt
git write-tree # hash6 tree
echo 'Second commit' | git commit-tree hash6 -p hash4 # hash7 commit

git log hash7
# to make it more convinient
git update-ref refs/heads/master hash7
git update-ref refs/heads/test-branch hash7
```

<!-- slide vertical=true data-background-image="./1-current-status.jpg" -->
<!-- slide vertical=true data-background-image="./2-add-new-file.jpg" -->
<!-- slide vertical=true data-background-image="./3-git-add.jpg" -->
<!-- slide vertical=true data-background-image="./4-git-commit.jpg" -->

<!-- slide -->

## Back to the Questions


<!-- slide vertical=true -->

#### staging area

- 白话：staging area
- 黑话：.git/index

<!-- slide vertical=true -->

#### tracked & untracked

- 白话：untracked means a file not exist in the previous commit and of course not staged
- 黑话；untracked 是指该文件还未被执行 hash-object/update-index，即没有被添加到 .git/index

<!-- slide vertical=true -->

#### modified & unmodified

- 白话：modified means a tracked file has been modified in the working directory but not yet staged
- 黑话: modified 是指该文件被改动后还未被执行 hash-object/update-index，即没有被添加到 .git/index

<!-- slide vertical=true -->

#### 切换分支为什么很快

因为分支的存储和文件的存储是完全分离的，git 追踪的文件内容本身存储与 `.git/objects` 文件夹下，而对分支的操作是对 `.git/refs` 的操作。新建一个分支仅仅是创建一个包含 commit 类型的文件的 SHA1 值（40个字符长度）的文件，如：

```shell
➜  test git:(master) cat .git/refs/heads/test-branch
058cdaf0b58a13b5945548da3054087e9f9b265c
```

<!-- slide vertical=true data-background-image="./4-git-commit.jpg" -->

<div style="height:800px;position:relative;">
  <div style="position:absolute;bottom:0;right:20px;font-size:80%;text-align: right;">
    <p><strong>git reflog 原理</strong></p>
    <p style="font-size:60%">.git/logs: .git/HEAD 的时间机器</p>
    <p style="font-size:60%">command: git reflog or git log -g</p>
  </div>
</div>

<!-- slide vertical=true -->

#### 如果 .git/log 被删除了
#### 时光机器还能工作吗

<!-- slide vertical=true -->

#### 可以 ！ `git fsck --full`
寻找那些散落人间的未被引用的 git 文件<br/>其中就有以前提交的 commit 文件

<!-- slide vertical=true -->

#### 增量存储 or 全量存储

<!-- slide vertical=true -->

- 初期是全量存储
- git gc
  - 从记录全量内容的 blob 文件，转为记录不同 commit 间 delta 的 pack 文件
  - git verify-pack -v .git/objects/pack/xxxxx
  - git 也会自动触发 gc 行为，触发条件未知

<!-- slide -->

## Have fun

<!-- slide vertical=true -->

[Sina - 继Linux和Git之后，Linus官宣新作品直指元宇宙](https://k.sina.com.cn/article_5044281310_12ca99fde02001qzf9.html)

<iframe src="https://k.sina.com.cn/article_5044281310_12ca99fde02001qzf9.html" seamless style="height:1080px;width:100%;"></iframe>

<!-- slide vertical=true -->

- [Github Security - Impersonating a user through git email address](https://bounty.github.com/ineligible.html#impersonating_a_user_through_git_email_address)
  - report to github or signing with GPG key
- Github fork machanism makes `github.com/user/repo/tree/commit-hash` can reach out to commit not inside the repo

<!-- slide -->

## The END
