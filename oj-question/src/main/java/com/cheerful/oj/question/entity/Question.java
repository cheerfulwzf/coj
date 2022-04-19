package com.cheerful.oj.question.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Question)表实体类
 *
 * @author makejava
 * @since 2022-03-12 16:05:22
 */
@TableName("qms_question")
public class Question extends Model<Question> {
    
    private Long id;
    
    private String content;
    
    private String title;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    
    private Integer diffLevel;

    @TableField(fill = FieldFill.INSERT)
    private Integer submitCount;
    
    private String outSamples;
    
    private String inSamples;

    @TableField(fill = FieldFill.INSERT)
    private Integer isShow;

    private Double timeLimit;

    private Double memoryLimit;

    @TableField(exist = false)
    private List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Double getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Double timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Double getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Double memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getDiffLevel() {
        return diffLevel;
    }

    public void setDiffLevel(Integer diffLevel) {
        this.diffLevel = diffLevel;
    }

    public Integer getSubmitCount() {
        return submitCount;
    }

    public void setSubmitCount(Integer submitCount) {
        this.submitCount = submitCount;
    }

    public String getOutSamples() {
        return outSamples;
    }

    public void setOutSamples(String outSamples) {
        this.outSamples = outSamples;
    }

    public String getInSamples() {
        return inSamples;
    }

    public void setInSamples(String inSamples) {
        this.inSamples = inSamples;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
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

