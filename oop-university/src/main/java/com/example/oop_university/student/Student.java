package com.example.oop_university.student;

import com.example.oop_university.course.Course;

public abstract class Student {

    protected int maxCredits;
    protected String name;

    public boolean canEnroll(Course course) {
        return course.getCredits() <= maxCredits;
    }
}
