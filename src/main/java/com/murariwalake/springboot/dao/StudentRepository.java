package com.murariwalake.springboot.dao;

import com.murariwalake.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Student findStudentByEmail(String email);
}
