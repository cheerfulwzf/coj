package com.cheerful.oj.platform.pojo.dto;


import lombok.Data;

import java.util.Date;

@Data
public class Question {
    private Long id;
    
    private String content;
    
    private String title;

    private Date gmtCreate;

    private Date gmtModified;
    
    private Integer diffLevel;

    private Integer submitCount;
    
    private String outSamples;
    
    private String inSamples;

    private Integer isShow;

    private Double timeLimit;

    private Double memoryLimit;
}

