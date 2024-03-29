package com.murariwalake.springboot.dao;

import java.util.List;

import com.murariwalake.springboot.entity.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOImpl implements StudentDAO {
	private final EntityManager entityManager;

	public StudentDAOImpl(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<StudentEntity> getAllStudents() {
		return entityManager.createQuery("SELECT s FROM StudentEntity s", StudentEntity.class).getResultList();
	}

	@Override
	public StudentEntity getStudentById(Long studentId) {
		return entityManager.find(StudentEntity.class, studentId);
	}


	@Override
	@Transactional
	public StudentEntity addStudent(StudentEntity student) {
		return entityManager.merge(student);
	}

	@Override
	@Transactional
	public StudentEntity updateStudent(StudentEntity student) {
		return entityManager.merge(student);
	}

	@Override
	@Transactional
	public void deleteStudent(Long studentId) {
		StudentEntity student = getStudentById(studentId);
		entityManager.remove(student);
	}

	@Override
	public StudentEntity getStudentByEmail(String email) {
		try {
			return entityManager.createQuery("SELECT s FROM StudentEntity s WHERE s.email = :email", StudentEntity.class)
					.setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException e) {
			return null; // Return null if no student found with the provided email
		}
	}
}
