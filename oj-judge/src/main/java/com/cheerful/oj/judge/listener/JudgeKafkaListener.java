package com.cheerful.oj.judge.listener;

import com.cheerful.oj.common.dto.JudgeTaskDTO;
import com.cheerful.oj.judge.service.JudgeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @Copyright: www.xiaojukeji.com Inc. All rights reserved.
 * @Description:
 * @Date: 10/8/22 3:16 PM
 * @Author: wangzhifu
 */
@Service
public class JudgeKafkaListener {

	private final JudgeService judgeService;

	public JudgeKafkaListener(JudgeService judgeService) {
		this.judgeService = judgeService;
	}

	@KafkaListener
	public void judgeTaskHandler(JudgeTaskDTO taskDTO) {
		judgeService.judge(taskDTO);
	}
}
