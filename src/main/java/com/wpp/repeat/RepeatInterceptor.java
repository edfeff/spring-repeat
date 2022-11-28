package com.wpp.repeat;

import com.wpp.repeat.annotation.Repeat;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;

/**
 * @author wpp
 * @desc
 * @see
 * @since 2022/11/28
 */
public class RepeatInterceptor implements MethodInterceptor {
    protected final Log logger = LogFactory.getLog(getClass());
    private final Repeat repeat;

    public RepeatInterceptor(Repeat repeat) {
        this.repeat = repeat;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        int times = repeat.value();
        Method method = invocation.getMethod();

        Throwable lastThrowable = null;
        Object result = null;

        for (int i = 1; i <= times; i++) {
            try {
                logger.debug("Repeat method:" + method.getName() + ",[" + i + "/" + times + "]");
                result = invocation.proceed();
            } catch (Throwable e) {
                if (repeat.ignoreException()) {
                    lastThrowable = e;
                } else {
                    throw e;
                }
            }
        }

        if (lastThrowable != null) {
            throw lastThrowable;
        }

        return result;
    }
}
