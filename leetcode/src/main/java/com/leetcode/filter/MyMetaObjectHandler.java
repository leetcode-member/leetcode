package com.leetcode.filter;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * mybatis-plus 填充器
 * @author Jarvan
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入操作的时候执行的自动填充方法
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("========进入了insertFill方法============");
        //通过名称设置filed自动填充的值 setFieldValByName
//      源码参数  setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject) {
        //这里错误过，insert要更updateTime 就要在这里添加.
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
        // 0 代表默认不删除!!
        this.setFieldValByName("deleted",0,metaObject);
    }

    /**
     * 修改的时候执行的 自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("========进入了insertFill方法============");
        //通过名称设置filed自动填充的值 setFieldValByName
//      源码参数  setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
