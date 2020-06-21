package com.mall.exception;

import com.mall.utils.ConstantUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Global Throwable Handler
 * </p>
 *
 * @author yanglin
 * @date 2020-06-20 23:52:36
 */
@RestControllerAdvice
public class GlobalThrowableHandler {

    /**
     * <p>
     * Handle Throwable
     * </p>
     *
     * @param t Throwable
     * @return ResponseEntity<Map < String, Object>>
     * @author yanglin
     * @date 2020-06-21 00:31:50
     */
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    private ResponseEntity<Map<String, Object>> handleThrowable(Throwable t) {
        Map<String, Object> result = new HashMap<>(ConstantUtil.RESULT_MAP_INIT_COUNT);
        t.printStackTrace();
        result.put("errorMessage", t.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    //    /**
    //     * <p>
    //     * 描述: 约束校验异常-字段为空以及字段值重复异常
    //     * </p>
    //     * <p>
    //     * 创建时间: 2019-11-21 16:35
    //     * </p>
    //     * <p>
    //     * 更新时间: 2019-11-21 16:35
    //     * </p>
    //     * <p>
    //     * 更新者: yanglin
    //     * </p>
    //     *
    //     * @param e       异常对象
    //     * @param request 请求域
    //     * @return Map
    //     *
    //     * @author yanglin
    //     */
    //    @ResponseBody
    //    @ExceptionHandler(ConstraintViolationException.class)
    //    @ResponseStatus(HttpStatus.LOCKED)
    //    private Map<String, Object> constraintViolationException(ConstraintViolationException e,
    //            HttpServletRequest request) {
    //        e.printStackTrace();
    //        String message = "";
    //        String[] ms = e.getSQLException().getMessage().split("'");
    //        if (ms[0].indexOf("Column") != -1) {
    //            // 字段为空的异常
    //            message = "字段'" + ms[1] + "'的值不能为空";
    //        } else if (ms[0].indexOf("Duplicate entry") != -1) {
    //            // 字段值重复的异常
    //            message = "字段值'" + ms[1] + "'重复";
    //        }
    //        return ResultMap.state(null, HttpStatus.LOCKED, message);
    //    }
}
