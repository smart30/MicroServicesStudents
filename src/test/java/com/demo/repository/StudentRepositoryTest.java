package com.demo.repository;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.demo.model.Student;
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class StudentRepositoryTest {
	@Autowired
	StudentRepository studentRepository;
	
	Student student;
	@BeforeEach
	void setUp() throws Exception {
		student = Student.builder().id(5).name("Sowmya").age(25).email("sowmya@gmail.com").build();
	}

	@Test
	void testFindAllStudents() {
		List<Student> students =  new ArrayList<Student>();
		studentRepository.findAll().forEach(s ->  students.add(s));
		Assertions.assertEquals(4, students.size());
	}

	@Test
	void testFindStudentById() {
		Student studentResp = studentRepository.save(student);
		Assertions.assertEquals(studentResp, studentRepository.findById(studentResp.getId()).get());
		
	}

	@Test
	void testCreateStudent() {
		studentRepository.save(student);
		List<Student> students =  new ArrayList<Student>();
		studentRepository.findAll().forEach(s ->  students.add(s));
		Assertions.assertEquals(5, students.size());
		
	}

	@Test
	void testUpdateStudent() {
		studentRepository.save(student);
		List<Student> students =  new ArrayList<Student>();
		studentRepository.findAll().forEach(s ->  students.add(s));
		Assertions.assertEquals(5, students.size());
		
		student.setName("Sowmya New");
		studentRepository.save(student);
		List<Student> updateStudents =  new ArrayList<Student>();
		studentRepository.findAll().forEach(s ->  updateStudents.add(s));
		Assertions.assertEquals(5, updateStudents.size());
	}

	@Test
	void testDeleteStudent() {
		Student studentResp = studentRepository.save(student);
		List<Student> students =  new ArrayList<Student>();
		studentRepository.findAll().forEach(s ->  students.add(s));
		Assertions.assertEquals(5, students.size());
		
		studentRepository.deleteById(studentResp.getId());
		
		List<Student> deleteStudents =  new ArrayList<Student>();
		studentRepository.findAll().forEach(s ->  deleteStudents.add(s));
		Assertions.assertEquals(4, deleteStudents.size());
		
		
	}

}
