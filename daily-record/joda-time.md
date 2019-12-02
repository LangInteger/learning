# Joda Time API

## 1 主要定义

### 1.1 日期时间相关对象

- LocalDate 不是指“当地日期”，而是止仅包含日期 (date without time)
- LocalTime 不是指“当地时间”，而是止仅包含时间 (time without date)
- Instant 是指时间线上的一瞬间，可以类比于 `System.currentMilliseconds` 获取的时间戳，精确到毫秒
- DateTime 代表日期时间，同时包含了时区信息，不同时区的 DateTime 可能代表相同的 Instant

### 1.2 时区

从 IANA 管理的[时区数据库](https://www.iana.org/time-zones)获取时区数据，每个地方的时区可能由于政治实体作出的决定而改变。比较典型的动态时区是某些地区实行的“夏令时”。
