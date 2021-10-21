package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Student;
import com.demo.service.StudentService;
@RestController
public class StudentControllerImpl implements StudentController{
	@Autowired
	StudentService studentService;
	@GetMapping("/student")
	@Override
	public ResponseEntity findAllStudents() {
		return new ResponseEntity(studentService.findAllStudents(), HttpStatus.OK );
	}
	
	@GetMapping("/student/{studentId}")
	@Override
	public ResponseEntity findStudentById(@PathVariable Integer studentId) {
		return new ResponseEntity(studentService.findStudentById(studentId), HttpStatus.OK );
	}

	@PostMapping("/student")
	@Override
	public ResponseEntity createStudent(@RequestBody Student student) {
		return new ResponseEntity(studentService.createStudent(student), HttpStatus.CREATED );
	}
	
	@PutMapping("/student/{studentId}")
	@Override
	public ResponseEntity updateStudent(@PathVariable Integer studentId, @RequestBody Student student) {
		return new ResponseEntity(studentService.updateStudent(studentId, student), HttpStatus.OK );
	}
	
	@DeleteMapping("/student/{studentId}")
	@Override
	public void deleteStudent(@PathVariable Integer studentId) {
		studentService.deleteStudent(studentId);
	}

	
}
