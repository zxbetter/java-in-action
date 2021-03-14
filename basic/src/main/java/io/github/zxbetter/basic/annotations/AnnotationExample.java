package io.github.zxbetter.basic.annotations;

import java.lang.annotation.Annotation;

/**
 * Annotation Example
 *
 * @author zxbetter 2021/3/14 13:41
 */
@Annotation2("annotation2")
public class AnnotationExample {

    public static void main(String[] args) {

        for (Annotation annotation : AnnotationExample.class.getAnnotations()) {

            // 获取注解上的注解
            for (Annotation annotation1 : annotation.annotationType().getAnnotations()) {
                System.out.println(annotation1);
            }
            System.out.println(annotation);
        }
    }
}
