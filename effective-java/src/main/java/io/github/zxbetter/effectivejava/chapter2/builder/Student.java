package io.github.zxbetter.effectivejava.chapter2.builder;

import lombok.Getter;
import lombok.ToString;

/**
 * student
 *
 * @author devin.zhang
 */
@ToString(callSuper = true)
@Getter
public class Student extends Person {
    private String schoolName;

    public static class Builder extends Person.Builder<Builder> {
        private String schoolName;

        public Builder(String name) {
            super(name);
        }

        public Builder setSchoolName(String schoolName) {
            this.schoolName = schoolName;
            return self();
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Student build() {
            return new Student(this);
        }
    }

    private Student(Builder builder) {
        super(builder);
        this.schoolName = builder.schoolName;
    }
}
