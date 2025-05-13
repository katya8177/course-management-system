package com.example.courses.controller;

import com.example.courses.model.Course;
import com.example.courses.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> createCourse(
            @RequestBody Course course,
            @RequestHeader("X-Teacher-Id") Long teacherId) {
        return ResponseEntity.ok(courseService.createCourse(course, teacherId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }
}
