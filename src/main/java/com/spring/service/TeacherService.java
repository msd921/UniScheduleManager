package com.spring.service;

import com.spring.converter.ScheduleConverter;
import com.spring.converter.TeacherConverter;
import com.spring.dao.ScheduleJdbcDao;
import com.spring.dao.TeacherJdbcDao;
import com.spring.dto.ScheduleDto;
import com.spring.dto.TeacherDto;
import com.spring.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherJdbcDao teacherJdbcDao;
    private final TeacherConverter teacherConverter;
    private final ScheduleJdbcDao scheduleJdbcDao;
    private final ScheduleConverter scheduleConverter;

    public List<TeacherDto> getAll() {
        return teacherJdbcDao.getAll().stream()
                .map(teacherConverter::toDto)
                .collect(Collectors.toList());
    }

    public void create(TeacherDto teacherDto) {
        Teacher teacher = teacherConverter.toEntity(teacherDto);
        teacherJdbcDao.save(teacher);
    }

    public void delete(int id) {
        teacherJdbcDao.delete(id);
    }

    public TeacherDto findById(int id) {
        Teacher teacher = teacherJdbcDao.findById(id);
        return teacherConverter.toDto(teacher);
    }

    public void edit(TeacherDto teacherDto) {
        Teacher teacher = teacherConverter.toEntity(teacherDto);
        teacherJdbcDao.update(teacher);
    }

    public List<ScheduleDto> getSchedule(int teacherId) {
        return teacherJdbcDao.getScheduleByTeacherId(teacherId).stream()
                .map(scheduleConverter::toDto)
                .collect(Collectors.toList());
    }
}
