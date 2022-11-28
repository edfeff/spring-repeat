package com.wpp.repeat.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wpp
 * @desc
 * @see
 * @since 2022/11/28
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Repeat {
    /**
     * 重试的次数
     *
     * @return
     */
    int value() default 1;

    /**
     * 是否忽略中间的异常
     * 仅仅抛出最后一次异常信息
     *
     * @return
     */
    boolean ignoreException() default true;
}
