package com.example.oop_university.enrollment;

import com.example.oop_university.student.Student;
import com.example.oop_university.course.Course;

public class Enrollment {

    private final Student student;
    private final Course course;
    private EnrollmentStatus status;

    public Enrollment(Student student, Course course) {
        System.out.println("Creating enrollment for " + student);
        this.student = student;
        this.course = course;
        this.status = EnrollmentStatus.PENDING;
    }

    public void process() {
        if (student.canEnroll(course)) {
            status = EnrollmentStatus.APPROVED;
        } else {
            status = EnrollmentStatus.REJECTED;
        }
    }

    public EnrollmentStatus getStatus() {
        return status;
    }
}
