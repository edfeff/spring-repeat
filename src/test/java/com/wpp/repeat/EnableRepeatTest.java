package com.wpp.repeat;

import com.wpp.repeat.annotation.EnableRepeat;
import com.wpp.repeat.annotation.Repeat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


class EnableRepeatTest {

    @Test
    public void testRepeat() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
        RepeatConfiguration repeatConfiguration = context.getBean(RepeatConfiguration.class);
        Assertions.assertNotNull(repeatConfiguration);

        Service service = context.getBean(Service.class);
        Assertions.assertTrue(AopUtils.isAopProxy(service));

        Foo foo = context.getBean(Foo.class);
        Assertions.assertFalse(AopUtils.isAopProxy(foo));

        service.service();
        Assertions.assertEquals(9, service.getCount());
    }

    @Configuration
    @EnableRepeat
    protected static class TestConfiguration {

        @Bean
        public Service innerService() {
            return new Service();
        }

        @Bean
        public Foo innerFoo() {
            return new Foo();
        }
    }

    static class Service {
        private int count = 0;

        public int getCount() {
            return count;
        }

        @Repeat(value = 9)
        public void service() {
            count++;
        }
    }

    static class Foo {
    }
}