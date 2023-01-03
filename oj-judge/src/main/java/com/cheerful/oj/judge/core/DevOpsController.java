package com.cheerful.oj.judge.core;

import com.cheerful.oj.common.dto.JudgeTaskDTO;
import com.cheerful.oj.common.vo.Result;
import com.cheerful.oj.judge.core.factory.base.JudgeHandler;
import com.cheerful.oj.judge.core.service.JudgeFactory;
import com.cheerful.oj.judge.core.service.JudgeFactory.JudgeTypeVO;
import com.cheerful.oj.judge.core.service.JudgeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: coj
 * @DATE: 2022/12/9 15:15
 * @DESCRIPTION:
 */
@RestController
@RequestMapping("/judge")
public class DevOpsController {

  @Autowired
  private JudgeFactory judgeFactory;

  @Autowired
  private JudgeService judgeService;

  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }

  @GetMapping("/listAllJudgeType")
  public Result<List<JudgeTypeVO>> listAllJudgeType() {
    return Result.success(judgeFactory.listAllJudgeType());
  }

  @GetMapping("/getJudgeDetail/{code}")
  public Result<JudgeTypeVO> getJudgeDetail(@PathVariable Integer code) {
    JudgeHandler judgeHandler = judgeFactory.createJudgeHandler(code);
    return Result.success(JudgeTypeVO.of(judgeHandler));
  }

  @PostMapping("/core")
  public void judgeCore(@RequestBody JudgeTaskDTO taskDTO) {
    judgeService.judge(taskDTO);
  }

}
