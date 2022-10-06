package com.cheerful.oj.common.dto;

import java.util.List;
import lombok.ToString;

@ToString
public class JudgeResultDTO {

	private String globalMsg;
	private Long submissionId;
	private List<ResultCaseDTO> result;

	public JudgeResultDTO() {
	}

	public JudgeResultDTO(String globalMsg, List<ResultCaseDTO> result) {
		this.globalMsg = globalMsg;
		this.result = result;
	}

	public String getGlobalMsg() {
		return globalMsg;
	}

	public void setGlobalMsg(String globalMsg) {
		this.globalMsg = globalMsg;
	}

	public List<ResultCaseDTO> getResult() {
		return result;
	}

	public void setResult(List<ResultCaseDTO> result) {
		this.result = result;
	}

	public Long getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}
}