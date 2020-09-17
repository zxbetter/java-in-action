package io.github.zxbetter.basic;

/**
 * String Example
 *
 * @author zxbetter
 */
public class StringExample {

    public static class StringBuilderExample {
        public void testIndex() {
            StringBuilder builder = new StringBuilder();
            builder.append("1234");

            // 下标是从0开始
            builder.deleteCharAt(builder.length() - 1);

            System.out.println(builder.toString());
        }
    }

    public static void main(String[] args) {
        new StringBuilderExample().testIndex();
    }
}
