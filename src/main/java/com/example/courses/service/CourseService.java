package com.example.courses.service;

import com.example.courses.model.Course;
import com.example.courses.model.User;
import com.example.courses.repository.CourseRepository;
import com.example.courses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public Course createCourse(Course course, Long teacherId) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        
        if (!teacher.getRole().equals(UserRole.TEACHER)) {
            throw new RuntimeException("Only teachers can create courses");
        }
        
        course.setTeacher(teacher);
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }
}
