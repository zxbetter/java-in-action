package cn.zxbetter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于生成类的 getter 方法
 *
 * {@link Target} 设置该注解只能作用于类
 * {@link Retention} 设置该注解只能在编译期其作用
 *
 * @author xin.zhang
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Getter {
}
