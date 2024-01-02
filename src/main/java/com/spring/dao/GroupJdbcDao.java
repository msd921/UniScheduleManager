package com.spring.dao;

import com.spring.mapper.ScheduleRowMapper;
import com.spring.model.Course;
import com.spring.model.Group;
import com.spring.model.Schedule;
import com.spring.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class GroupJdbcDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Group> getAll() {
        return jdbcTemplate.query("SELECT * FROM groups", new BeanPropertyRowMapper<>(Group.class));
    }

    public void save(Group group) {
        jdbcTemplate.update("INSERT INTO groups(name) VALUES(?)", group.getName());
    }

    public void update(Group group) {
        jdbcTemplate.update("UPDATE groups SET name = ? WHERE id = ?", group.getName(), group.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM course_group WHERE group_id = ?", id);
        jdbcTemplate.update("DELETE FROM groups WHERE id = ?", id);
    }

    public Group findById(int id) {
        return jdbcTemplate.query("SELECT * FROM groups WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Group.class))
                .stream().findAny().orElse(null);
    }

    public Group findByName(String name) {
        return jdbcTemplate.query("SELECT id FROM groups WHERE name = ?", new Object[]{name}, new BeanPropertyRowMapper<>(Group.class))
                .stream().findAny().orElse(null);
    }

    public List<Student> getStudentsInGroup(int id) {
        String sql = "SELECT s.id, s.name, s.surname, s.group_id, g.name AS group_name " +
                "FROM students s " +
                "LEFT JOIN groups g ON s.group_id = g.id " +
                "WHERE s.group_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Student.class))
                .stream().collect(Collectors.toList());
    }

    public void addGroupToCourse(int courseId, int groupId) {
        jdbcTemplate.update("INSERT INTO course_group (course_id, group_id) VALUES (?, ?)", courseId, groupId);
    }

    public List<Course> getCoursesForGroup(int id) {
        String sql = "SELECT c.id, c.name, c.description " +
                "FROM courses c " +
                "JOIN course_group cg ON c.id = cg.course_id " +
                "WHERE cg.group_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Course.class))
                .stream().collect(Collectors.toList());
    }

    public List<Schedule> getScheduleForGroup(int groupId) {
        String sql = "SELECT s.id AS schedule_id, s.date, " +
                "g.id AS group_id, g.name AS group_name, " +
                "t.id AS teacher_id, t.name AS teacher_name, t.surname AS teacher_surname, " +
                "c.id AS course_id, c.name AS course_name, c.description AS course_description " +
                "FROM schedules s " +
                "LEFT JOIN groups g ON s.group_id = g.id " +
                "LEFT JOIN courses c ON s.course_id = c.id " +
                "LEFT JOIN teachers t ON c.teacher_id = t.id " +
                "WHERE s.group_id = ?";
        return jdbcTemplate.query(sql, new Object[]{groupId}, new ScheduleRowMapper());
    }

}
