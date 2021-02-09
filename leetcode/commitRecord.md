# 记录必要 commit 更新说明

## 2021.02.04

### 3. jarvan/fix-token：添加 token controller 层测试用例

测试类位置 `src/test/java/com/leetcode/token/TokenApiTest03.java`

### 2. jarvan/feature-token：完善 token

1. **token 中包含的 3 个数据**

```json
{
  "userId": "432153151535",
  "userRole": "ROLE_ADMIN",
  "timeStamp" "1341241531"
}
```

而且这三个参数的参数名都用 TokenConstant.java 进行规范

userRole 的 2 个参数 用 UserRoleConstant.java 进行规范

2. 全局异常捕获 GlobalExceptionHandler.java 

3. token 判断和过期判断（过期返回 401）

   ![image-20210204214337068](commitRecord.assets/image-20210204214337068.png)

   token 过期返回 401
   ![image-20210204214800856](commitRecord.assets/image-20210204214800856.png)

### 1. jarvan/feature-environment:更新数据库；修改实体类；测试插入部分数据
* 我们 id 使用雪花算法 + UUID，在 MyMetaObjectHandler 中自动实现，所以我们设置 id 的时候不用设置 id 的值。因为这个需要使用到 Long 长整型，所以 我们修改数据库，修改实体类

* 修改实体类的自动填充器，自动填充  `createTime`、`updateTime`、`deleted`字段

* 测试插入部分数据 tag list user
