package com.spring.dao;


import com.spring.mapper.StudentRowMapper;
import com.spring.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentJdbcDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Student> getAll() {
        String sql = "SELECT s.id, s.name, s.surname, s.group_id, g.name AS groupName " +
                "FROM students s " +
                "LEFT JOIN groups g ON s.group_id = g.id";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }

    public void save(Student student) {
        jdbcTemplate.update("INSERT INTO students(name, surname, group_id) VALUES(?,?,?)", student.getName(), student.getSurname(), student.getGroup().getId());
    }

    public void update(Student student) {
        jdbcTemplate.update("UPDATE students SET name = ?, surname = ?, group_id = ? WHERE id = ?", student.getName(), student.getSurname(), student.getGroup().getId(), student.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM students WHERE id = ?", id);
    }

    public Student findById(int id) {
        String sql = "SELECT s.id, s.name, s.surname, s.group_id, g.name AS groupName " +
                "FROM students s " +
                "LEFT JOIN groups g ON s.group_id = g.id " +
                "WHERE s.id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new StudentRowMapper())
                .stream().findAny().orElse(new Student());
    }
}
