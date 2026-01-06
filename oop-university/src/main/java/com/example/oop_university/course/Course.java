package com.example.oop_university.course;

public class Course {

    private final String name;
    private final int credits;

    public Course(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    public int getCredits() {
        return credits;
    }
}