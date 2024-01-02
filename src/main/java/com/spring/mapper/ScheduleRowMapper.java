package com.spring.mapper;

import com.spring.model.Course;
import com.spring.model.Group;
import com.spring.model.Schedule;
import com.spring.model.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleRowMapper implements RowMapper<Schedule> {

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setId(rs.getInt("schedule_id"));
        schedule.setDate(rs.getTimestamp("date").toLocalDateTime());

        Group group = new Group();
        group.setId(rs.getInt("group_id"));
        group.setName(rs.getString("group_name"));
        schedule.setGroup(group);

        Teacher teacher = new Teacher();
        teacher.setId(rs.getInt("teacher_id"));
        teacher.setName(rs.getString("teacher_name"));
        teacher.setSurname(rs.getString("teacher_surname"));
        schedule.setTeacher(teacher);

        Course course = new Course();
        course.setId(rs.getInt("course_id"));
        course.setName(rs.getString("course_name"));
        course.setDescription(rs.getString("course_description"));
        schedule.setCourse(course);

        return schedule;
    }
}
