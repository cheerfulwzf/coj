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
 * (Discuss)表实体类
 *
 * @author makejava
 * @since 2022-03-10 17:04:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ums_discuss")
public class Discuss extends Model<Discuss> {

	private Long id;

	private Long authorId;

	private String content;

	@TableField(fill = FieldFill.INSERT)
	private Date gmtCreate;

	@TableField(fill = FieldFill.INSERT)
	private Integer favorNum;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date gmtModified;

	private String title;

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

