package com.murariwalake.springboot.service;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.murariwalake.springboot.dao.StudentDAO;
import com.murariwalake.springboot.entity.StudentEntity;
import com.murariwalake.springboot.exception.StudentClientException;
import com.murariwalake.springboot.model.StudentGET;
import com.murariwalake.springboot.model.StudentPOST;
import com.murariwalake.springboot.model.StudentPUT;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentDAO studentDAO;
	private final ObjectMapper objectMapper;

	public StudentServiceImpl(final StudentDAO studentDAO, final ObjectMapper objectMapper) {
		this.studentDAO = studentDAO;
		this.objectMapper = objectMapper;
	}

	@Override
	public List<StudentGET> getAllStudents() {
		return objectMapper.convertValue(studentDAO.getAllStudents(), objectMapper.getTypeFactory().constructCollectionType(List.class, StudentGET.class));
	}

	@Override
	public StudentGET getStudentById(Long studentId) {
		return objectMapper.convertValue(getStudentEntityById(studentId), StudentGET.class);
	}

	private StudentEntity getStudentEntityById(Long studentId) {
		StudentEntity studentEntity = studentDAO.getStudentById(studentId);
		if (studentEntity == null) {
			throw new StudentClientException("Student with id " + studentId + " not found", HttpStatus.NOT_FOUND);
		}
		return studentEntity;
	}

	@Override
	public StudentGET addStudent(StudentPOST student) {
		checkIfEmailIsAlreadyInUse(student.getEmail());
		StudentEntity studentEntity = objectMapper.convertValue(student, StudentEntity.class);
		return objectMapper.convertValue(studentDAO.addStudent(studentEntity), StudentGET.class);
	}

	private void checkIfEmailIsAlreadyInUse(String email) {
		if (studentDAO.getStudentByEmail(email) != null) {
			throw new StudentClientException("Email " + email + "is already in use", HttpStatus.CONFLICT);
		}
	}

	@Override
	public StudentGET updateStudent(Long studentId, StudentPUT studentPUT) {
		StudentEntity studentEntity = getStudentEntityById(studentId);
		
		StudentEntity studentEntityByEmailId = studentDAO.getStudentByEmail(studentPUT.getEmail());
		if (studentEntityByEmailId != null && !studentEntityByEmailId.getId().equals(studentId)) {
			throw new StudentClientException("Email " + studentPUT.getEmail() + " is already in use", HttpStatus.CONFLICT);
		}

		if (studentPUT.getName() != null) {
			studentEntity.setName(studentPUT.getName());
		}
		
		if (studentPUT.getEmail() != null) {
			studentEntity.setEmail(studentPUT.getEmail());
		}
		
		return objectMapper.convertValue(studentDAO.updateStudent(studentEntity), StudentGET.class);
	}

	@Override
	public void deleteStudent(Long studentId) {
		getStudentEntityById(studentId);
		studentDAO.deleteStudent(studentId);
	}
}
