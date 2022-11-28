这是一个使用注解来声明需要重复执行某方法的项目，是基于 Spring AOP 实现的。

## 起步

下面的代码展示了如何使用

```java

import com.wpp.repeat.annotation.EnableRepeat;
import com.wpp.repeat.annotation.Repeat;

@Configuration
@EnableRepeat
public class Application {

}

@Service
class Service {
    @Repeat(value = 10)
    public void service() {
        // ... do something
    }
}
```

- 通过配置类引入`@EnableRepeat`注解，可以开启重复机制。
- 在需要的方法或类上标注`@Repeat`注解，即可开启重复执行

## 构建此项目

此项目需要 Java 1.8 和 Maven 3.0.5 或以上，构建命令如下

```
$ mvn install
```

## 参与开发

通过fork此项目，修改后提交PR即可