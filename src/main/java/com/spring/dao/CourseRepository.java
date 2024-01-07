package com.spring.dao;

import com.spring.model.Course;
import com.spring.model.Group;
import com.spring.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Teacher getTeacherById(int teacherId);

}
