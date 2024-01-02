package com.spring.dao;

import com.spring.mapper.ScheduleRowMapper;
import com.spring.model.Schedule;
import com.spring.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeacherJdbcDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Teacher> getAll() {
        return jdbcTemplate.query("SELECT * FROM teachers", new BeanPropertyRowMapper<>(Teacher.class));
    }

    public void save(Teacher teacher) {
        jdbcTemplate.update("INSERT INTO teachers(name, surname) VALUES(?,?)", teacher.getName(), teacher.getSurname());
    }

    public void update(Teacher teacher) {
        jdbcTemplate.update("UPDATE teachers SET name = ?, surname = ? WHERE id = ?", teacher.getName(), teacher.getSurname(), teacher.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM teachers WHERE id = ?", id);
    }

    public Teacher findById(int id) {
        return jdbcTemplate.query("SELECT * FROM teachers WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Teacher.class))
                .stream().findAny().orElse(new Teacher());
    }

    public List<Schedule> getScheduleByTeacherId(int teacherId) {
        String sql = "SELECT s.id AS schedule_id, s.date, " +
                "g.id AS group_id, g.name AS group_name, " +
                "t.id AS teacher_id, t.name AS teacher_name, t.surname AS teacher_surname, " +
                "c.id AS course_id, c.name AS course_name, c.description AS course_description " +
                "FROM schedules s " +
                "LEFT JOIN groups g ON s.group_id = g.id " +
                "LEFT JOIN courses c ON s.course_id = c.id " +
                "LEFT JOIN teachers t ON c.teacher_id = t.id " +
                "WHERE t.id = ?";
        return jdbcTemplate.query(sql, new Object[]{teacherId}, new ScheduleRowMapper());
    }
}
