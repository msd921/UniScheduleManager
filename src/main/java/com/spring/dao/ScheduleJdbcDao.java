package com.spring.dao;

import com.spring.mapper.ScheduleRowMapper;
import com.spring.model.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleJdbcDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Schedule> getAll() {
        String sql = "SELECT s.id AS schedule_id, s.date, " +
                "g.id AS group_id, g.name AS group_name, " +
                "t.id AS teacher_id, t.name AS teacher_name, t.surname AS teacher_surname, " +
                "c.id AS course_id, c.name AS course_name, c.description AS course_description " +
                "FROM schedules s " +
                "LEFT JOIN groups g ON s.group_id = g.id " +
                "LEFT JOIN courses c ON s.course_id = c.id " +
                "LEFT JOIN teachers t ON c.teacher_id = t.id";

        return jdbcTemplate.query(sql, new ScheduleRowMapper());
    }

    public void save(Schedule schedule) {
        jdbcTemplate.update("INSERT INTO schedules(date,course_id,group_id) VALUES(?,?,?) ", schedule.getDate(), schedule.getCourse().getId(), schedule.getGroup().getId());
    }

    public void update(Schedule schedule) {
        jdbcTemplate.update("UPDATE schedules SET date = ?, course_id = ?, group_id = ? WHERE id = ?", schedule.getDate(), schedule.getCourse().getId(), schedule.getGroup().getId(), schedule.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM schedules WHERE id = ?", id);
    }

    public Schedule findById(int id) {
        String sql = "SELECT s.id AS schedule_id, s.date, " +
                "g.id AS group_id, g.name AS group_name, " +
                "t.id AS teacher_id, t.name AS teacher_name, t.surname AS teacher_surname, " +
                "c.id AS course_id, c.name AS course_name, c.description AS course_description " +
                "FROM schedules s " +
                "LEFT JOIN groups g ON s.group_id = g.id " +
                "LEFT JOIN courses c ON s.course_id = c.id " +
                "LEFT JOIN teachers t ON c.teacher_id = t.id WHERE s.id = ?";

        return jdbcTemplate.query(sql, new Object[]{id}, new ScheduleRowMapper())
                .stream().findAny().orElse(new Schedule());
    }

    public boolean courseGroupExists(int courseId, int groupId) {
        String sql = "SELECT COUNT(*) FROM course_group WHERE course_id = ? AND group_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, courseId, groupId) > 0;
    }

    public boolean isScheduleTimeCorrectForCreate(Schedule schedule) {
        String sql = "SELECT COUNT(*) FROM schedules " +
                "WHERE group_id = ? " +
                "AND (date, date + interval '1 hour') OVERLAPS (?, ?)";
        return jdbcTemplate.queryForObject(sql, Integer.class, schedule.getGroup().getId(),
                schedule.getDate(), schedule.getDate()) <= 0;
    }

    public boolean isScheduleTimeCorrectForEdit(Schedule schedule){
        String sql = "SELECT COUNT(*) FROM schedules " +
                "WHERE group_id = ? AND id <> ? " +
                "AND (date, date + interval '1 hour') OVERLAPS (?, ?)";
        return jdbcTemplate.queryForObject(sql, Integer.class, schedule.getGroup().getId(),
                schedule.getId(), schedule.getDate(), schedule.getDate()) <= 0;
    }

}
