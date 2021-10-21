package com.demo.controller;

import org.springframework.http.ResponseEntity;

import com.demo.model.Student;

public interface StudentController {
	ResponseEntity findAllStudents();
	
	ResponseEntity findStudentById(Integer studentId);
	
	ResponseEntity createStudent(Student student);
	
	ResponseEntity updateStudent(Integer studentId, Student student);
	
	void deleteStudent(Integer studentId);
}
