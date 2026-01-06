package com.example.oop_university.student;

public class UndergraduateStudent extends Student {

    public UndergraduateStudent(String name) {
        System.out.println("Creating undergraduate student " + name);
        this.maxCredits = 20;
    }
}