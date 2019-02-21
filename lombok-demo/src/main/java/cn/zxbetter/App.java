package cn.zxbetter;

import lombok.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * lombok demo
 * @author xin.zhang
 */
public class App {
    public static void main(String[] args) {
        System.out.println("lombok demo");
    }

    /**
     * #1 {@link val} 声明一个 final 的 <code>局部</code> 变量，根据 <code>初始化表达式</code> 自动推断变量的类型。
     */
    public void testVal() {
        val a = "a";

        // 1. 必须在具有初始化表达式的变量声明中使用
        // val e;
        // e = 1;

        // 2. 对于复合类型的判断，取共同继承的父类，而不是共同实现的接口
        // final AbstractCollection<String> b = (AbstractCollection<String>)((new Random().nextInt() % 2 == 0) ? new HashSet<Object>() : new ArrayList<Object>());
        // val b = new Random().nextInt() % 2 == 0 ? new HashSet<String>() : new ArrayList<String>();

        // 3. 在类型不明确时，取 java.lang.Object
        // final Object c = null;
        val c = null;
    }

    /**
     * #2 {@link var} 除了不是 final 的变量，其他和 {@link val} 一样。
     */
    public void testVar() {
        var a = "a";
    }

    /**
     * #3 {@link NonNull} 用于方法或构造方法的参数。
     * 如果参数为 null 则抛出空指针异常。
     * 如果参数是基本类型，在编译时会提示警告，且不会生成对应的检查语句。
     *
     * @param a 参数 a
     * @param b 参数 b
     */
    public void testNonNull(@NonNull String a, @NonNull int b) {
        System.out.println(a);
        System.out.println(b);
    }

    /**
     * #4 {@link Cleanup} 生成关闭流等资源的代码。
     * 默认调用的是资源的 <code>close()</code> 方法，
     * 如果关闭资源用的是其他方法，可以在注解中指定，如 @Cleanup("dispose")
     */
    public void testCleanup() throws IOException {
        @Cleanup InputStream in = new FileInputStream("d:\\1.png");
        @Cleanup OutputStream out = new FileOutputStream("d:\\2.png");
        byte[] b = new byte[10000];
        int r = 0;
        while ((r = in.read(b)) != -1) {
            out.write(b, 0, r);
        }
    }

    /**
     * #5 {@link lombok.Getter} 和 {@link Setter}
     * 可用于类或类的字段上，用于字段上会生成对应的 getter/setter，用于类上会为类的所有非 static 字段生成 getter/setter
     *
     * 1. 默认的作用用是 public，可以在注解中指定对应的作用域 - PUBLIC, PROTECTED, PACKAGE, 和 PRIVATE
     *
     * 2. 当注解放在类上时，可以在特定的字段上指定 <code>@Getter(AccessLevel.NONE)</code> 或 <code>@Setter(AccessLevel.NONE)</code>
     * 标识该字段不用生成对应的 getter/setter
     */
    class TestGetterSetter {

        /**
         * 3. 注释将被拷贝到对应的 getter 和 setter 方法中
         * -- SETTER --
         * (两个破折号 + 空格 + GETTER/SETTER + 空格 + 两个破折号) 这个格式可以为对应的 getter/setter 方法设置不同的注释
         *
         * @param 指定的注释会被拷贝到 setter 方法的注释中
         * @return 指定的注释会被拷贝到 getter 方法的注释中
         */
        @Getter @Setter(AccessLevel.PRIVATE) private String name;

        /**
         * 4. boolean 类型的字段，生成的 getter 方法是以 is 为前缀的。
         */
        @Getter
        @Setter
        private boolean a;

        /**
         * 5. boolean 类型的字段，如果字段本身是 is 为前缀，则生成的 getter 方法不加其他前缀。
         */
        @Getter
        @Setter
        private boolean isAbc;
    }

    /**
     * #6 {@link ToString} 生成类的 toString 方法，
     * 会打印类的名称和非静态字段及其值。格式为 "App.TestToString(foo=" + this.foo + ")"
     *
     * 1. 指定 includeFieldNames = false 时，打印格式为 "App.TestToString(" + this.foo + ")"
     *
     * 2. 指定 onlyExplicitlyIncluded = true 时，类的字段中有 @ToString.Include 注解的才会被打印
     *
     * 3. 指定 callSuper = true 时，会调用父类的 toString 方法，
     * 格式为 "App.TestToString(super=" + super.toString() + ", foo=" + this.foo + ")"
     */
    @ToString
    class TestToString extends TestGetterSetter {
        private String foo;

        /**
         * 4. 在 toString 方法中排除该字段
         */
        @ToString.Exclude
        private int age;

        /**
         * 5. 可以在 toString 方法中调用其他成员方法，该成员方法不能有参数
         * "App.TestToString(foo=" + this.foo + ", print=" + this.print() + ")"
         */
        @ToString.Include
        private String print() {
            return "abc";
        }

        /**
         * 6. 可以指定别名
         * 7. 可以指定打印的先后顺序，默认是 0，rank 的值越大越先打印
         * "App.TestToString(some=" + this.other + ", foo=" + this.foo + ", print=" + this.print() + ")"
         */
        @ToString.Include(name = "some", rank = 10)
        private String other;
    }

    /**
     * #7 简化异常抛出
     */
    class TestSneakyThrows implements Runnable {
        @SneakyThrows(UnsupportedEncodingException.class)
        public String utf8ToString(byte[] bytes) {
            return new String(bytes, "UTF-8");
        }

        @Override
        @SneakyThrows
        public void run() {
            throw new Throwable();
        }
    }
}
