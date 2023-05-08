package com.school.service;

import java.util.List;

import com.school.repository.AdminRepository;
import com.school.repository.StudentRepository;
import com.school.repository.TeacherRepository;
import com.school.to.UserTo;

public class AdminService {

	AdminRepository adminRepository = new AdminRepository();

	StudentRepository studentRepository = new StudentRepository();

	TeacherRepository teacherRepository = new TeacherRepository();

	// find all students
	public List<UserTo> getStudentsAndTeachers() {
		return adminRepository.findAllStudentsAndTeachers();
	}

	// checks updation user
	public boolean changeStudentUser(UserTo user) {
		if (studentRepository.changeStudent(user) != 0)
			return true;

		return false;

	}

	// checks updation for teacher
	public boolean changeAdminTeacher(UserTo user) {

		if (teacherRepository.changeTeacher(user) != 0)
			return true;

		return false;
	}
}
//by PRAKARSH KAUSHIK (2536930)