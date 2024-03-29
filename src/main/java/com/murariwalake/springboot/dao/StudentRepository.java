package com.murariwalake.springboot.dao;

import com.murariwalake.springboot.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
	StudentEntity findStudentByEmail(String email);
}
