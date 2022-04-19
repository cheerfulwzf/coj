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
 * (UserLoginLog)表实体类
 *
 * @author makejava
 * @since 2022-03-10 17:04:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ums_user_login_log")
public class UserLoginLog extends Model<UserLoginLog> {

    private Long id;

    private Long uId;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    private String ip;

    private String city;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUId() {
        return uId;
    }

    public void setUId(Long uId) {
        this.uId = uId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

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

