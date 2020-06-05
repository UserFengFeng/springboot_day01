package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller基类 所有web端controller都继承此类 包含配置信息、包装返回数据等
 */
public class BaseController {

  /**
   * slf4j
   */
  protected Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * 请求成功返回
   *
   * @param value
   * @return JSON {"success":Object}
   */
  protected Map<String, Object> successResult(Object... value) {
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("flag", "success");
    if (value.length > 0) {
      result.put("result", value[0]);
    }
    return result;
  }

  protected Map<String, Object> successResult2(Object... value) {
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("flag", "success");
    if (value.length == 1) {
      result.put("result", value[0]);
    } else if (value.length == 2) {
      result.put("result", value[0]);
      result.put("message", value[1]);
    }
    return result;
  }

  /**
   * 请求失败时返回
   *
   * @param errorMessage 提示信息
   * @return 封装了两个入参的map，前端获取为JSON
   */
  public Map<String, Object> failedResult(String... errorMessage) {
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("flag", "failed");
    if (errorMessage.length > 0) {
      result.put("message", errorMessage[0]);
    } else {
      result.put("message", "系统异常");
    }
    return result;
  }

}
