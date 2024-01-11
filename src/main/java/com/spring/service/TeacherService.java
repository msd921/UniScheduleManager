package com.spring.service;

import com.spring.converter.ScheduleConverter;
import com.spring.converter.TeacherConverter;
import com.spring.dao.TeacherRepository;
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

    private final TeacherRepository teacherRepository;
    private final TeacherConverter teacherConverter;
    private final ScheduleConverter scheduleConverter;

    public List<TeacherDto> getAll() {
        return teacherRepository.findAll().stream()
                .map(teacherConverter::toDto)
                .collect(Collectors.toList());
    }

    public void create(TeacherDto teacherDto) {
        Teacher teacher = teacherConverter.toEntity(teacherDto);
        teacherRepository.save(teacher);
    }

    public void delete(int id) {
        teacherRepository.deleteById(id);
    }

    public TeacherDto findById(int id) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        return teacherConverter.toDto(teacher);
    }

    public void edit(TeacherDto teacherDto) {
        Teacher teacher = teacherConverter.toEntity(teacherDto);
        teacherRepository.save(teacher);
    }

    public List<ScheduleDto> getSchedule(int teacherId) {
        List<ScheduleDto> schedules = teacherRepository.findSchedulesByTeacherId(teacherId).stream()
                .map(scheduleConverter::toDto)
                .collect(Collectors.toList());
        if(schedules.isEmpty()){
            throw new RuntimeException("Schedules is empty");
        }
        return schedules;
    }
}
