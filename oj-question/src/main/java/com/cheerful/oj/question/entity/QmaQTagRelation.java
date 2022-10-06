package com.cheerful.oj.question.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (QmaQTagRelation)表实体类
 *
 * @author makejava
 * @since 2022-03-12 16:05:22
 */
@TableName("qms_q_tag_relation")
public class QmaQTagRelation extends Model<QmaQTagRelation> {

	private Long id;

	private Long qid;

	private Long tid;

	private String tValue;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQid() {
		return qid;
	}

	public void setQid(Long qid) {
		this.qid = qid;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getTValue() {
		return tValue;
	}

	public void setTValue(String tValue) {
		this.tValue = tValue;
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

