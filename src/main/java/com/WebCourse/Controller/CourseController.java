package com.WebCourse.Controller;

import com.WebCourse.Model.Course;
import com.WebCourse.Service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping("/")
    public String index() {
        // This looks for index.html in the static or templates folder
        return "forward:/index.html";
    }

    @PostMapping("/addcourses")
    public List<Course> addCourses(@RequestBody List<Course> courses) {
        return service.addCourses(courses);
    }

    @PostMapping("/addcourse")
    public Course addCourse(@Valid @RequestBody Course course){
        return service.addCourse(course);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return service.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable Long courseId) {
        return service.getCourseById(courseId);
    }
    @PutMapping("/{courseId}")
    public Course updateCourse(@PathVariable Long courseId, @Valid @RequestBody Course course) {
        return service.updateCourse(courseId, course);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable Long courseId) {
        service.deleteCourse(courseId);
    }
}