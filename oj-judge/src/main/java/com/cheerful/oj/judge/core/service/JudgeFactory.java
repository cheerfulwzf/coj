package com.cheerful.oj.judge.core.service;

import com.cheerful.oj.judge.core.factory.base.JudgeHandler;
import com.cheerful.oj.judge.core.factory.impl.GoJudgeHandler;
import com.cheerful.oj.judge.core.factory.impl.JSJudgeHandler;
import com.cheerful.oj.judge.core.factory.impl.JavaJudgeHandler;
import com.cheerful.oj.judge.core.factory.impl.OtherJudgeHandler;
import com.cheerful.oj.judge.core.factory.impl.Python2JudgeHandler;
import com.cheerful.oj.judge.core.factory.impl.Python3JudgeHandler;
import com.cheerful.oj.judge.core.factory.impl.cpp.GNUCPP11JudgeHandler;
import com.cheerful.oj.judge.core.factory.impl.cpp.GNUCPP14JudgeHandler;
import com.cheerful.oj.judge.core.factory.impl.cpp.GNUCPP17JudgeHandler;
import com.cheerful.oj.judge.core.factory.impl.gcc.GNUC90JudgeHandler;
import com.cheerful.oj.judge.core.factory.impl.gcc.GNUC99JudgeHandler;
import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/11 13:59
 * @DESCRIPTION:
 */
@Component
public class JudgeFactory {

  private final JavaJudgeHandler java;

  private final GNUC90JudgeHandler c90;

  private final GNUC99JudgeHandler c99;

  private final GNUCPP11JudgeHandler cpp11;

  private final GNUCPP14JudgeHandler cpp14;

  private final GNUCPP17JudgeHandler cpp17;

  private final GoJudgeHandler go;

  private final JSJudgeHandler js;

  private final Python2JudgeHandler py2;

  private final Python3JudgeHandler py3;

  private final OtherJudgeHandler other;

  private final Map<Integer, JudgeHandler> judgeHandlerMap = new HashMap<>();

  @Autowired
  public JudgeFactory(OtherJudgeHandler other, JavaJudgeHandler java, GNUC90JudgeHandler c90,
      GNUC99JudgeHandler c99, GNUCPP11JudgeHandler cpp11, GNUCPP14JudgeHandler cpp14,
      GNUCPP17JudgeHandler cpp17, GoJudgeHandler go, JSJudgeHandler js, Python2JudgeHandler py2,
      Python3JudgeHandler py3) {
    this.java = java;
    this.c90 = c90;
    this.c99 = c99;
    this.cpp11 = cpp11;
    this.cpp14 = cpp14;
    this.cpp17 = cpp17;
    this.go = go;
    this.js = js;
    this.py2 = py2;
    this.py3 = py3;
    this.other = other;
  }

  /**
   * 创建响应的语言的模板
   *
   * @param orderType 选择哪种语言？ 0--->Java
   * @return 最终各语言的判题
   */
  public JudgeHandler createJudgeHandler(Integer orderType) {
    switch (orderType) {
      case 0:
        return java;
      case 1:
        return c90;
      case 2:
        return c99;
      case 3:
        return cpp11;
      case 4:
        return cpp14;
      case 5:
        return cpp17;
      case 6:
        return go;
      case 8:
        return py2;
      case 9:
        return py3;
      case 10:
        return js;
      default:
        other.init(orderType.toString());
        return other;
    }
  }

  public List<JudgeTypeVO> listAllJudgeType() {
    List<JudgeTypeVO> list = Lists.newArrayList();
    Set<Map.Entry<Integer, JudgeHandler>> entries = judgeHandlerMap.entrySet();
    entries.forEach(entry -> {
      JudgeTypeVO judgeTypeVO = JudgeTypeVO.of(entry.getValue());
      judgeTypeVO.setCode(entry.getKey());
      list.add(judgeTypeVO);
    });
    Set<Map.Entry<String, List<String>>> entrySet = other.getMap().entrySet();
    entrySet.forEach(entry -> {
      other.init(entry.getKey());
      String name = Optional.ofNullable(entry.getValue().get(0)).orElse("");
      JudgeTypeVO judgeTypeVO = new JudgeTypeVO.JudgeTypeVOBuilder()
          .code(Integer.parseInt(entry.getKey()))
          .name(name)
          .compileCmd(other.getConfigureCompilerCmd())
          .runCmd(other.getConfigureRunCmd())
          .build();
      list.add(judgeTypeVO);
    });
    return list;
  }

  @Data
  @Builder
  public static class JudgeTypeVO {

    private Integer code;
    private String name;
    private String compileCmd;
    private String runCmd;

    public static JudgeTypeVO of(JudgeHandler handler) {
      return new JudgeTypeVO.JudgeTypeVOBuilder()
          .name(handler.getClass().getSimpleName().replace("JudgeHandler", ""))
          .runCmd(handler.getConfigureRunCmd())
          .compileCmd(handler.getConfigureCompilerCmd())
          .build();
    }

  }

  @PostConstruct
  private void initJudgeHandlerMap() {
    judgeHandlerMap.put(0, java);
    judgeHandlerMap.put(1, c90);
    judgeHandlerMap.put(2, c99);
    judgeHandlerMap.put(3, cpp11);
    judgeHandlerMap.put(4, cpp14);
    judgeHandlerMap.put(5, cpp17);
    judgeHandlerMap.put(6, go);
    judgeHandlerMap.put(8, py2);
    judgeHandlerMap.put(9, py3);
    judgeHandlerMap.put(10, js);
  }
}
