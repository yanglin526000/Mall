package com.mall.file.exception;

import com.mall.common.exception.GlobalThrowableHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * File Exception Handler
 * </p>
 *
 * @author yanglin
 * @date 2020-06-29 23:53:57
 */
@RestControllerAdvice
public class ExceptionHandler extends GlobalThrowableHandler {
}
