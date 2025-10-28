package com.ZidioIntern.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ZidioIntern.DTO.CourseDTO;
import com.ZidioIntern.Entity.Course;
import com.ZidioIntern.Repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class CourseService {

	@Autowired
	private CourseRepository courseRepo;
	
	public CourseDTO addCourse(CourseDTO dto) {
		Course course = new Course();
		course.setCourseTitle(dto.getCourseTitle());
		course.setCourseDescription(dto.getCourseDescription());
		course.setCourCatogory(dto.getCourCatogory());
		course.setAdminId(dto.getAdminId());
		course.setCreatedAt(LocalDateTime.now());
		course.setActive(true);
		
		courseRepo.save(course);
		
		dto.setId(course.getId());
		dto.setAdminId(course.getAdminId());
		dto.setActive(course.isActive());
		
		return dto;
		
	}
	
	public List<CourseDTO>getAllActiveCourses() {
		return courseRepo.findByActiveTrue().stream().map(c->{
			CourseDTO dto = new CourseDTO();
			dto.setId(c.getId());
			dto.setCourseTitle(c.getCourseTitle());
			dto.setCourseDescription(c.getCourseDescription());
			dto.setCourCatogory(c.getCourCatogory());
			dto.setAdminId(c.getAdminId());
			dto.setActive(c.isActive());
			
			return dto;
		}).collect(Collectors.toList());
	}
	
	public void deActiveCourse(Long id) {
		Course course = courseRepo.findById(id).orElseThrow(()->new RuntimeException("Course not Found.!"));
		course.setActive(false);
		
		courseRepo.save(course);
	}
	
}
