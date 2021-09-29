# Mysql

## 1 InnoDb 如何解决幻读的问题

其实真正来讲，mysql 并没有解决幻读，参考：

- [InnoDB Repeatable Read隔离级别之大不同](http://mysql.taobao.org/monthly/2017/06/07/)
- [Innodb 中 RR 隔离级别能否防止幻读？](https://github.com/Yhzhtk/note/issues/42)

### Lock

- Record Locks: Record locks always lock index records, even if a table is defined with no indexes.
  - The row-level locks are actually index-record locks
  - Example: `SELECT c1 FROM t WHERE c1 = 10 FOR UPDATE;`
  - 疑问：如果 c1 字段不是索引字段会产生怎么样的反应？
    - 会对c1字段对应的主键索引加行锁，同时对扫描到的行的主键索引加上间隙锁，组成 next-key lock
- Gap Locks
  - It is a lock on a gap:
    - between index records
    - on the gap before the first or after the last index record
  - Only used in some transaction isolation levels and not others
    - In transaction isolation level **READ COMMITTED**, gap locking is disabled for searches and index scans
    - **REPEATABLE READ** isolation level of InnoDB uses next-key locks for searches and index scans, which prevents [phantom rows](#phantom-rows)
  - Not needed for statements that lock rows using a unique index to search for a unique row
  - Conflicting locks can be held on a gap by different transactions
  - Only purpose is to prevent other transactions from inserting to the gap
- Next-Key Locks
  - A conbination of a record lock on the index record and a gap lock on the gap before the index record.
  - 判断锁范围
    - Unique Index
      - 等值查询命中：退化为行锁，只锁一行
      - 等值查询未命中：退化为间隙锁，范围是前后两个值的开区间，退化为 Gap Lock
      - 范围查询：会对范围加锁。至于是否要退化为 Gap Lock，要视 Mysql 版本而定
    - Non-Unique Index
      - 等值查询命中：退化为间隙锁，锁定前后两个值的开区间。根据查询是否仅包含主键值，还决定是否要给主键索引加锁
      - 范围查询：会对范围加锁，不会有边界退化为行锁的行为

## Phantom Rows

The same query produces different sets of rows at different times.

- 幻读仅专指新插入的行，由于行数据 Update 操作带来的不算
  
InnoDB 的 Repeatable Read isolation level 解决幻读问题从两个方面入手：

- 普通查询是快照读（MVCC），无法看到其他事务插入的数据
  - MVCC 工作机制下，同一条记录在系统中可以存在多个版本，可以通过回滚日志（redo-log）来到达某个版本的状态。减少长事务可以减少回滚日志的体积，因为回滚日志是在当系统判断没有事务在需要用到这些回滚日志的时候被删除掉（mysql5.5之前回滚日志和数据字典一起放在ibdata文件，即使回滚段被清理，数据也不会变小）。
- for update / for share 查询是当前读，如果不做特殊处理，是有可能看到其他事务新写入的数据，产生幻读问题。针对此 RR 在行锁基础上引入了 Next-Key Lock，用来解决幻读问题

## Mysql 文档中的错误

[Mysql Gap Locks](https://dev.mysql.com/doc/refman/8.0/en/innodb-locking.html#innodb-next-key-locks)文档中有如下文字描述：

> For example, if the id column has a unique index, the following statement uses only an index-record lock for the row having id value 100 and it does not matter whether other sessions insert rows in the preceding gap:
> SELECT * FROM child WHERE id = 100;

很明显这里的语句给错了，应该是 `SELECT * FROM child WHERE id = 100 FOR UPDATE;`。
