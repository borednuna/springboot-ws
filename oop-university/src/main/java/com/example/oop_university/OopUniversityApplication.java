package com.example.oop_university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.oop_university.course.Course;
import com.example.oop_university.enrollment.Enrollment;
import com.example.oop_university.student.Student;
import com.example.oop_university.student.UndergraduateStudent;

@SpringBootApplication
public class OopUniversityApplication {

	public static void main(String[] args) {
		Student student = new UndergraduateStudent("Erick");
		Course course = new Course("Algorithms", 18);

		Enrollment enrollment = new Enrollment(student, course);
		enrollment.process();

		System.out.println(enrollment.getStatus());
	}

}
