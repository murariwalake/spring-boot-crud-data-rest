package com.murariwalake.springboot.service;

import java.util.List;

import com.murariwalake.springboot.entity.StudentEntity;
import com.murariwalake.springboot.model.StudentGET;
import com.murariwalake.springboot.model.StudentPOST;
import com.murariwalake.springboot.model.StudentPUT;

public interface StudentService {

	List<StudentGET> getAllStudents();

	StudentGET getStudentById(Long studentId);

	StudentGET addStudent(StudentPOST student);

	StudentGET updateStudent(Long studentId, StudentPUT student);

	void deleteStudent(Long studentId);



}
