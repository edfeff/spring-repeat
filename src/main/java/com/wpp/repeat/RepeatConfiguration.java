package com.wpp.repeat;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Component;

/**
 * @author wpp
 * @desc
 * @see
 * @since 2022/11/28
 */
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Component
public class RepeatConfiguration extends AbstractPointcutAdvisor implements InitializingBean {
    private Pointcut pointcut;
    private Advice advice;

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.pointcut = buildPoint();
        this.advice = buildAdvice();
    }

    private Advice buildAdvice() {
        return new RepeatAdvice();
    }

    private Pointcut buildPoint() {
        return new RepeatPointcut();
    }
}
