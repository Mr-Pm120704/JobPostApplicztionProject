package com.ZidioIntern.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ZidioIntern.DTO.CourseDTO;
import com.ZidioIntern.Service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor

public class CourseController {

	@Autowired
	private CourseService courseserv;
	
	@PostMapping("/api/{adminId}")
	public ResponseEntity<CourseDTO>addCourse(@RequestBody CourseDTO dto){
		return ResponseEntity.ok(courseserv.addCourse(dto));
	}
	
	@PostMapping
	public ResponseEntity<List<CourseDTO>>getCourse(@RequestBody CourseDTO dto){
		return ResponseEntity.ok(courseserv.getAllActiveCourses());
	}
	
	@PutMapping("/deActivate/{id}")
	public ResponseEntity<String>deactivateCourse(@PathVariable Long id){
		courseserv.deActiveCourse(id);
		return ResponseEntity.ok("Course Deactivate successfully");
	}

	
}
