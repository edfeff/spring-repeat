package com.wpp.repeat;

import com.wpp.repeat.annotation.Repeat;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

/**
 * @author wpp
 * @desc
 * @see
 * @since 2022/11/28
 */
public class RepeatPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        Repeat repeatOnMethod = AnnotationUtils.findAnnotation(method, Repeat.class);
        Repeat repeatOnClass = AnnotationUtils.findAnnotation(targetClass, Repeat.class);
        return repeatOnMethod != null || repeatOnClass != null;
    }
}
