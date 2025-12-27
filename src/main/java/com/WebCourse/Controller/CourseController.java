package com.WebCourse.Controller;

import com.WebCourse.Model.Course;
import com.WebCourse.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    //POST_ALL
    @PostMapping("/addcourses")
    public List<Course> addCourses(@RequestBody List<Course> courses) {
        return service.addCourses(courses);
    }
    //POST-SINGLE COURSE
    @PostMapping("/addcourse")
    public Course addCourse(@RequestBody Course course){
        return service.addCourse(course);
    }

    // Get ALL courses
    @GetMapping
    public List<Course> getAllCourses() {
        return service.getAllCourses();
    }

    // Get course by ID
    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable Long courseId) {
        return service.getCourseById(courseId);
    }

    // Delete course
    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable Long courseId) {
        service.deleteCourse(courseId);
    }
}
