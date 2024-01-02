package com.spring.mapper;

import com.spring.model.Course;
import com.spring.model.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id"));
        course.setName(rs.getString("name"));
        course.setDescription(rs.getString("description"));

        int teacherId = rs.getInt("teacher_id");
        if (teacherId > 0) {
            Teacher teacher = new Teacher();
            teacher.setId(teacherId);
            teacher.setName(rs.getString("teacher_name"));
            teacher.setSurname(rs.getString("teacher_surname"));
            course.setTeacher(teacher);
        }

        return course;
    }
}
