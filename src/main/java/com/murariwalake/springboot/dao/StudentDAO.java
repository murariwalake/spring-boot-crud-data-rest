package com.murariwalake.springboot.dao;

import java.util.List;

import com.murariwalake.springboot.entity.StudentEntity;

public interface StudentDAO {
	List<StudentEntity> getAllStudents();

	StudentEntity getStudentById(Long studentId);

	StudentEntity addStudent(StudentEntity student);

	StudentEntity updateStudent(StudentEntity student);

	void deleteStudent(Long studentId);

	StudentEntity getStudentByEmail(String email);
}
