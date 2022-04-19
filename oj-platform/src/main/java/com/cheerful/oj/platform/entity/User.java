package com.cheerful.oj.platform.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2022-03-10 17:04:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ums_user")
public class User extends Model<User> {

    private Long id;

    private String nickname;

    private String avatarUrl;

    private String email;

    private String password;

    private String userDesc;

    private Integer userRole;

    private Integer sex;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtModified;

    private Long socialId;

    private String accessToken;

    @TableField(fill = FieldFill.INSERT)
    private Integer prohabitStatus;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

