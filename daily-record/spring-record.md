# 记录 Spring Boot 的点点滴滴

## apply plugin: 'io.spring.dependency-management'

可以自动管理 spring 相关依赖的样子，能够识别下面这个版本配置块，不用在依赖中给每个 spring 相关的配置写明版本。

```text
    ext {
        // specify the spring boot gradle plugin version
        springBootVersion = '2.1.5.RELEASE'
    }
```
