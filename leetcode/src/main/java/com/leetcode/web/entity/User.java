package com.leetcode.web.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * user id
     */
    @TableId
    private Long userId;

    /**
     * 用户角色,springsecurity规定：ROLE_USER,ROLE_ADMIN
     */
    private String userRole;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     *  别名
     */
    private String nickname;

    /**
     *  性别(1男,0女)
     */
    private Integer  sex;

    /**
     * 头像图片地址
     */
    private String  avatar;

    /**
     *  邮箱地址
     */
    private String  email;

    /**
     *  做过的简单题的数量
     */
    private Integer  numSimp;

    /**
     *  做过的中等题的数量
     */
    private Integer  numMid;

    /**
     *  做过的复杂题的数量
     */
    private Integer  numDiff;

    /**
     * 注册时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date  createTime;

    /**
     *  账号信息更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date  updateTime;

    /**
     *  乐观锁
     */
    @Version
    private Integer version;

    /**
     *  逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer  deleted;


}
