package com.demo.service;

import java.util.List;

import com.demo.model.Student;

public interface StudentService {
	
	List<Student> findAllStudents();
	
	Student findStudentById(Integer studentId);
	
	Student createStudent(Student student);
	
	Student updateStudent(Integer studentId, Student student);
	
	void deleteStudent(Integer studentId);
}
