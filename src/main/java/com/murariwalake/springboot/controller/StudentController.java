package com.murariwalake.springboot.controller;

import java.util.List;

import com.murariwalake.springboot.model.StudentGET;
import com.murariwalake.springboot.model.StudentPOST;
import com.murariwalake.springboot.model.StudentPUT;
import com.murariwalake.springboot.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/students")
public class StudentController {
	private final StudentService studentService;

	public StudentController(final StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping()
	public ResponseEntity<List<StudentGET>> getAllStudents() {
		return ResponseEntity.ok(studentService.getAllStudents());
	}

	@GetMapping("/{studentId}")
	public ResponseEntity<StudentGET> getStudentById(@PathVariable Long studentId) {
		return ResponseEntity.ok(studentService.getStudentById(studentId));
	}

	@PostMapping()
	public ResponseEntity<StudentGET> addStudent(@RequestBody StudentPOST studentPOST) {
		return ResponseEntity.created(null).body(studentService.addStudent(studentPOST));
	}

	@PutMapping("/{studentId}")
	public ResponseEntity<StudentGET> updateStudent(@PathVariable Long studentId, @RequestBody StudentPUT studentPUT) {
		return ResponseEntity.ok(studentService.updateStudent(studentId, studentPUT));
	}

	@DeleteMapping("/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
		studentService.deleteStudent(studentId);
		return ResponseEntity.noContent().build();
	}
}
