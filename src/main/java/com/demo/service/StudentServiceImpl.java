package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.Student;
import com.demo.repository.StudentRepository;

public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student findStudentById(Integer studentId) {
		return studentRepository.findById(studentId).get();
	}

	@Override
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student updateStudent(Integer studentId, Student student) {
		student.setId(studentId);
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Integer studentId) {
		studentRepository.deleteById(studentId);
		
	}

	

}
