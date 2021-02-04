# 记录必要 commit 更新说明

## 2021.02.04
### jarvan/feature-environment:更新数据库；修改实体类；测试插入部分数据
* 我们 id 使用雪花算法 + UUID，在 MyMetaObjectHandler 中自动实现，所以我们设置 id 的时候不用设置 id 的值。因为这个需要使用到 Long 长整型，所以 我们修改数据库，修改实体类

* 修改实体类的自动填充器，自动填充  `createTime`、`updateTime`、`deleted`字段

* 测试插入部分数据 tag list user
 
