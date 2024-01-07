package com.spring.dao;

import com.spring.model.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleJdbcDao {

    private final JdbcTemplate jdbcTemplate;
    private final ScheduleRepository scheduleRepository;

    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public void update(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public void delete(int id) {
        scheduleRepository.deleteById(id);
    }

    public Schedule findById(int id) {
        return scheduleRepository.findById(id).orElse(null);
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
