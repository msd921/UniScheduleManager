package com.spring.dao;

import com.spring.mapper.CourseRowMapper;
import com.spring.model.Course;
import com.spring.model.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CourseJdbcDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Course> getAll() {
        return jdbcTemplate.query("SELECT * FROM courses", new BeanPropertyRowMapper<>(Course.class));
    }

    public void save(Course course) {
        jdbcTemplate.update("INSERT INTO courses(name, description, teacher_id) VALUES(?,?,?)", course.getName(), course.getDescription(), course.getTeacher().getId());
    }

    public void update(Course course) {
        jdbcTemplate.update("UPDATE courses SET name = ?, description = ?, teacher_id = ? WHERE id = ?", course.getName(), course.getDescription(), course.getTeacher().getId(), course.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM course_group WHERE course_id = ?", id);
        jdbcTemplate.update("DELETE FROM courses WHERE id = ?", id);
    }

    public Course findById(int id) {
        String sql = "SELECT c.id, c.name, c.description, " +
                "t.id AS teacher_id, t.name AS teacher_name, t.surname AS teacher_surname " +
                "FROM courses c " +
                "LEFT JOIN teachers t ON c.teacher_id = t.id " +
                "WHERE c.id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CourseRowMapper());
    }

    public List<Group> getGroupsInCourse(int courseId) {
        String sql = "SELECT g.id, g.name " +
                "FROM groups g " +
                "JOIN course_group cg ON g.id = cg.group_id " +
                "WHERE cg.course_id = ?";

        return jdbcTemplate.query(sql, new Object[]{courseId}, new BeanPropertyRowMapper<>(Group.class))
                .stream().collect(Collectors.toList());
    }
}
