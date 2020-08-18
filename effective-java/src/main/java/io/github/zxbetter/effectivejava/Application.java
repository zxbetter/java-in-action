package io.github.zxbetter.effectivejava;

import io.github.zxbetter.effectivejava.chapter2.builder.Student;

/**
 * effective java 学习示例
 *
 * @author devin.zhang
 */
public class Application {
    public static void main(String[] args) {

        // 建造者模式
        Student student = new Student.Builder("devin").setSchoolName("csu").setEmail("itdevin@163.com").build();
        System.out.println(student);
    }
}
