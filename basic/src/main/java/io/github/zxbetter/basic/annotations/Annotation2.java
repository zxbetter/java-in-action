package io.github.zxbetter.basic.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解2
 *
 * @author zxbetter 2021/3/14 14:10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Annotation1("annotation1")
public @interface Annotation2 {
    String value() default "";
}
