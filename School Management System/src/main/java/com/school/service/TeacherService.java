package com.school.service;

import com.school.repository.TeacherRepository;
import com.school.to.UserTo;

public class TeacherService {
	TeacherRepository teacherRepository = new TeacherRepository();

	// searches teacher by id
	public UserTo getTeacherById(int id) {
		return teacherRepository.findTeacherbyId(id);
	}
}
//by PRAKARSH KAUSHIK (2536930)