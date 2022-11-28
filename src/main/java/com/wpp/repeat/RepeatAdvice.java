package com.wpp.repeat;

import com.wpp.repeat.annotation.Repeat;
import com.wpp.repeat.annotation.Repeatable;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;

/**
 * @author wpp
 * @desc
 * @see
 * @since 2022/11/28
 */
public class RepeatAdvice implements IntroductionInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        MethodInterceptor delegate = getDelegate(invocation);
        if (delegate != null) {
            return delegate.invoke(invocation);
        } else {
            return invocation.proceed();
        }
    }

    private MethodInterceptor getDelegate(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        Repeat repeat = AnnotatedElementUtils.findMergedAnnotation(method, Repeat.class);
        if (repeat == null) {
            repeat = AnnotatedElementUtils.findMergedAnnotation(method.getDeclaringClass(), Repeat.class);
        }
        return repeat == null ? null : new RepeatInterceptor(repeat);
    }

    @Override
    public boolean implementsInterface(Class<?> intf) {
        return Repeatable.class.isAssignableFrom(intf);
    }

}
