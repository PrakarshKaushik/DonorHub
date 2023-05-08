package com.school.service;

import java.util.List;

import com.school.repository.StudentRepository;
import com.school.to.UserTo;

public class StudentService {

	StudentRepository studentRepository = new StudentRepository();

	// list all students
	public List<UserTo> getAllStudents() {
		return studentRepository.findAllStudents();
	}

}
//by PRAKARSH KAUSHIK (2536930)