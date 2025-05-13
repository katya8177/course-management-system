package com.example.courses.controller;

import com.example.courses.model.Enrollment;
import com.example.courses.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses/{courseId}/enroll")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<Enrollment> enrollStudent(
            @PathVariable Long courseId,
            @RequestHeader("X-Student-Id") Long studentId) {
        return ResponseEntity.ok(enrollmentService.enrollStudent(courseId, studentId));
    }
}
