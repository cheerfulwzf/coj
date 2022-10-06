package com.cheerful.oj.platform.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (ReplyDiscuss)表实体类
 *
 * @author makejava
 * @since 2022-03-10 17:04:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ums_reply_discuss")
public class ReplyDiscuss extends Model<ReplyDiscuss> {

	private Long id;

	private Long uId;

	private String nickname;

	private String content;

	@TableField(fill = FieldFill.INSERT)
	private Date gmtCreate;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date gmtModified;

	@TableField(fill = FieldFill.INSERT)
	private Integer favorNum;

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

