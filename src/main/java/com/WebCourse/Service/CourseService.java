package com.WebCourse.Service;

import com.WebCourse.Middleware.CourseNotFoundException;
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

    public List<Course> addCourses(List<Course> courses) {
        return repository.saveAll(courses);
    }

    public Course addCourse(Course course) {
        return repository.save(course);
    }

    public Course getCourseById(Long courseId){
        return repository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
    }

    public void deleteCourse(Long id) {
        if(!repository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        repository.deleteById(id);
    }
    public Course updateCourse(Long courseId, Course course) {
        Course existingCourse = repository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        // Update the fields
        existingCourse.setCourseName(course.getCourseName());
        existingCourse.setCourseDescription(course.getCourseDescription());
        existingCourse.setTags(course.getTags());
        existingCourse.setDuration(course.getDuration());
        existingCourse.setListedOn(course.getListedOn());
        existingCourse.setPrice(course.getPrice());

        return repository.save(existingCourse);
    }
}