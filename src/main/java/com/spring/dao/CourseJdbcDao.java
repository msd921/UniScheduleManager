package com.spring.dao;

import com.spring.model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CourseJdbcDao {

    private final CourseRepository courseRepository;

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

    public void update(Course course) {
        courseRepository.save(course);
    }

    public void delete(int id) {
        courseRepository.deleteById(id);
    }

    public Course findById(int id) {
        return courseRepository.findById(id).orElse(null);
    }

}
