package com.murariwalake.springboot.service;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.murariwalake.springboot.dao.StudentRepository;
import com.murariwalake.springboot.entity.StudentEntity;
import com.murariwalake.springboot.exception.StudentClientException;
import com.murariwalake.springboot.model.StudentGET;
import com.murariwalake.springboot.model.StudentPOST;
import com.murariwalake.springboot.model.StudentPUT;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final ObjectMapper objectMapper;

	public StudentServiceImpl(final StudentRepository studentDAO, final ObjectMapper objectMapper) {
		this.studentRepository = studentDAO;
		this.objectMapper = objectMapper;
	}

	@Override
	public List<StudentGET> getAllStudents() {
		return objectMapper.convertValue(studentRepository.findAll() , objectMapper.getTypeFactory().constructCollectionType(List.class, StudentGET.class));
	}

	@Override
	public StudentGET getStudentById(Long studentId) {
		return objectMapper.convertValue(findStudentEntityById(studentId), StudentGET.class);
	}

	private StudentEntity findStudentEntityById(Long studentId) {
		Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
		if (!studentEntity.isPresent()) {
			throw new StudentClientException("Student with id " + studentId + " not found", HttpStatus.NOT_FOUND);
		}
		return studentEntity.get();
	}

	@Override
	public StudentGET addStudent(StudentPOST student) {
		checkIfEmailIsAlreadyInUse(student.getEmail());
		StudentEntity studentEntity = objectMapper.convertValue(student, StudentEntity.class);
		return objectMapper.convertValue(studentRepository.save(studentEntity), StudentGET.class);
	}

	private void checkIfEmailIsAlreadyInUse(String email) {
		if (studentRepository.findStudentByEmail(email) != null) {
			throw new StudentClientException("Email " + email + "is already in use", HttpStatus.CONFLICT);
		}
	}

	@Override
	public StudentGET updateStudent(Long studentId, StudentPUT studentPUT) {
		StudentEntity studentEntity = findStudentEntityById(studentId);
		
		StudentEntity studentEntityByEmailId = studentRepository.findStudentByEmail(studentPUT.getEmail());
		if (studentEntityByEmailId != null && !studentEntityByEmailId.getId().equals(studentId)) {
			throw new StudentClientException("Email " + studentPUT.getEmail() + " is already in use", HttpStatus.CONFLICT);
		}

		if (studentPUT.getName() != null) {
			studentEntity.setName(studentPUT.getName());
		}
		
		if (studentPUT.getEmail() != null) {
			studentEntity.setEmail(studentPUT.getEmail());
		}
		
		return objectMapper.convertValue(studentRepository.save(studentEntity), StudentGET.class);
	}

	@Override
	public void deleteStudent(Long studentId) {
		findStudentEntityById(studentId);
		studentRepository.deleteById(studentId);
	}
}
