package com.demo.service;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.model.Student;
import com.demo.repository.StudentRepository;
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
	
	@Mock
	StudentRepository studentRepository;
	
	@InjectMocks
	StudentServiceImpl studentService;
	
	Student student;
	Student updatedStudent; 
	List<Student> studentList;
	@BeforeEach
	void setUp() throws Exception {
		student = Student.builder().id(5).name("Sowmya").age(25).email("sowmya@gmail.com").build();
		updatedStudent = Student.builder().id(5).name("Sowmya New").age(25).email("sowmya@gmail.com").build();
		
	}

	@Test
	void testFindAllStudents() {
		studentList =  Arrays.asList(student, updatedStudent);
		Mockito.when(studentRepository.findAll()).thenReturn(studentList);
		Assertions.assertEquals(studentList, studentService.findAllStudents());
	}

	@Test
	void testFindStudentById() {
		Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Assertions.assertEquals(student, studentService.findStudentById(student.getId()));
	}

	@Test
	void testCreateStudent() {
		Mockito.when(studentRepository.save(student)).thenReturn(student);
		Student studentResp = studentService.createStudent(student);
		Assertions.assertEquals(studentResp, student);
	}

	@Test
	void testUpdateStudent() {
		Mockito.when(studentRepository.save(student)).thenReturn(updatedStudent);
		Student studentResp = studentService.updateStudent(student.getId(), student);
		Assertions.assertEquals(studentResp, updatedStudent);
	}

	@Test
	void testDeleteStudent() {
		studentService.deleteStudent(student.getId());
		Mockito.verify(studentRepository, Mockito.times(1)).deleteById(student.getId());
	}

}
