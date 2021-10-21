package com.demo.controller;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.demo.model.Student;
import com.demo.service.StudentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest(StudentControllerImpl.class)
class StudentControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	StudentServiceImpl studentService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	Student student;
	Student updatedStudent;
	List<Student> studentList;
	
	@BeforeEach
	void setUp() throws Exception {
		student = Student.builder().id(5).name("Sowmya").age(25).email("sowmya@gmail.com").build();
		updatedStudent = Student.builder().id(5).name("Sowmya New").age(25).email("sowmya@gmail.com").build();
	}

	@Test
	void testFindAllStudents() throws Exception {
		studentList = Arrays.asList(student, updatedStudent);
		Mockito.when(studentService.findAllStudents()).thenReturn(studentList);
		mockMvc.perform(MockMvcRequestBuilders.get("/student")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					)
				.andExpect(MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(studentList)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}

	@Test
	void testFindStudentById() throws Exception{
		Mockito.when(studentService.findStudentById(student.getId())).thenReturn(updatedStudent);
		mockMvc.perform(MockMvcRequestBuilders.get("/student/"+student.getId())
				       .contentType(MediaType.APPLICATION_JSON)
					   .accept(MediaType.APPLICATION_JSON)
					   )
		.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(updatedStudent)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn();

	}

	@Test
	void testCreateStudent() throws JsonProcessingException, Exception {
		Mockito.when(studentService.createStudent(student)).thenReturn(student);
		mockMvc.perform(MockMvcRequestBuilders.post("/student")
				       .contentType(MediaType.APPLICATION_JSON)
					   .accept(MediaType.APPLICATION_JSON)
					   .content(objectMapper.writeValueAsString(student))
					   )
		.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(student)))
		.andExpect(MockMvcResultMatchers.status().isCreated())
		.andReturn();
	}

	@Test
	void testUpdateStudent() throws Exception {
		Mockito.when(studentService.updateStudent(student.getId(), student)).thenReturn(updatedStudent);
		mockMvc.perform(MockMvcRequestBuilders.put("/student/"+student.getId())
				       .contentType(MediaType.APPLICATION_JSON)
					   .accept(MediaType.APPLICATION_JSON)
					   .content(objectMapper.writeValueAsString(student))
					   )
		.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(updatedStudent)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn();
	}

	@Test
	void testDeleteStudent() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/student/"+student.getId())
			       .contentType(MediaType.APPLICATION_JSON)
				   .accept(MediaType.APPLICATION_JSON)
				   )
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn();
	}

}
