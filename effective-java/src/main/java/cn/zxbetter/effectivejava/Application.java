package cn.zxbetter.effectivejava;

import cn.zxbetter.effectivejava.chapter2.builder.Student;

/**
 * effective java 学习示例
 *
 * @author devin.zhang
 */
public class Application {
    public static void main(String[] args) {

        // 建造者模式
        Student student = new Student.Builder("jack").setSchoolName("csu").setEmail("itdevin@163.com").build();
        System.out.println(student);
    }
}
