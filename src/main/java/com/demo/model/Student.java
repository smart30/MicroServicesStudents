package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	@Id
	@Column
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private Integer age;
	
	@Column
	private String email;

}
