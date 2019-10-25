package cn.zxbetter.effectivejava.chapter2.builder;

import lombok.Getter;
import lombok.ToString;

/**
 * 建造者模式的优点：
 * 1）对比可伸缩构造方法模式（提供一个包含所有必需参数的构造方法，接着提供带有n个可选参数的构造方法，使得在构造方法中包含所有的参数），
 * 当参数很多时，可伸缩构造方法模式很难编写客户端代码，而且很难读懂它。
 * 2）对比JavaBeans模式（提供一个无参构造，提供所有参数的setter方法），JavaBeans模式在构造对象的时候，使得对象处于不一致的状态。
 *
 * 建造者模式的特点：
 * 1）Builder通常是它建造类的一个静态成员类
 * 2）提供一个包含所有必需参数的构造方法（或者静态工厂方法）用于客户端获取builder对象
 * 3）提供可选参数的setter方法
 * 4）提供一个无参的build方法来生成对象
 *
 * 使用场景：
 * 当类的构造方法或静态工厂方法的参数超过4个时，可以考虑使用建造者模式。
 *
 * @author devin.zhang
 */
@ToString
@Getter
public abstract class Person {
    private final String name;
    private String email;

    /**
     * 使用平行层次的结构，每个builder嵌套在相应的类中
     *
     * 模拟自我类型：Person.Builder是一个带有递归类型参数的泛型类型，这与抽象的self方法一起，允许方法链在子类中正常工作，而不需要强制类型转换。
     */
    public static abstract class Builder<T extends Builder<T>> {
        private String name;
        private String email;

        protected Builder(String name) {
            this.name = name;
        }

        public T setEmail(String email) {
            this.email = email;
            return self();
        }

        /**
         * Java缺乏自我类型，这里模拟自我类型
         */
        protected abstract T self();

        /**
         * 协变返回类型：子类重写（实现）父类方法，其声明的返回类型是父类声明返回类型的子类。
         */
        protected abstract Person build();
    }

    protected Person(Builder<?> builder) {
        this.name = builder.name;
        this.email = builder.email;
    }
}
