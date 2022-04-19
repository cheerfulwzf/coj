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
 * (Submission)表实体类
 *
 * @author makejava
 * @since 2022-03-10 17:04:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ums_submission")
public class Submission extends Model<Submission> {

    private Long id;

    private Long userId;

    private String nickname;

    private Long questionId;

    private String questionTitle;

    private Integer resultCode;

    private String resultMsg;

    private String resultInfo;

    private Double timeUsed;

    private Double memoryUsed;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    private Integer language;

    private String source;

    private String fromIp;

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

