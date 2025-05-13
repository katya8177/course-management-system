package com.example.courses.service;

import com.example.courses.model.*;
import com.example.courses.repository.EnrollmentRepository;
import com.example.courses.repository.CourseRepository;
import com.example.courses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public Enrollment enrollStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        
        if (!student.getRole().equals(UserRole.STUDENT)) {
            throw new RuntimeException("Only students can enroll");
        }
        
        if (enrollmentRepository.existsByCourseAndStudent(course, student)) {
            throw new RuntimeException("Student already enrolled");
        }
        
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudent(student);
        enrollment.setStatus(EnrollmentStatus.PENDING);
        
        return enrollmentRepository.save(enrollment);
    }
}
