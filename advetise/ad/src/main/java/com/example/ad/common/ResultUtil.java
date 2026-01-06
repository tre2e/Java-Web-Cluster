// com/example/ad/common/ResultUtil.java
package com.example.ad.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 简化版返回工具类（无泛型，避免类型不兼容）
 */
public class ResultUtil {

    // 成功返回
    public static Map<String, Object> success(String msg, Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }

    // 成功返回（无数据）
    public static Map<String, Object> success(String msg) {
        return success(msg, null);
    }

    // 失败返回
    public static Map<String, Object> error(String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("msg", msg);
        result.put("data", null);
        return result;
    }
}