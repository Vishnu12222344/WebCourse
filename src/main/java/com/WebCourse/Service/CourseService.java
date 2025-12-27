package com.WebCourse.Service;

import com.WebCourse.Model.Course;
import com.WebCourse.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CourseService {

    @Autowired
    private CourseRepository repository;

    public List<Course> getAllCourses(){
        return repository.findAll();
    }

    public List<Course> addCourses(List<Course> course) {
        return repository.saveAll(course);
    }
    public Course addCourse(Course course) {
        return repository.save(course);
    }
    public Course getCourseById(Long courseId){
        return repository.findById((long) courseId).orElseThrow(()->new RuntimeException("Cannot Find ID"));
    }

    public void deleteCourse(Long id) {
        repository.deleteById(id);
    }
}
