package com.spring.service;

import com.spring.converter.ScheduleConverter;
import com.spring.dao.ScheduleJdbcDao;
import com.spring.dto.ScheduleDto;
import com.spring.model.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleJdbcDao scheduleJdbcDao;

    private final ScheduleConverter scheduleConverter;

    public List<ScheduleDto> getAll() {
        return scheduleJdbcDao.getAll().stream()
                .map(scheduleConverter::toDto)
                .collect(Collectors.toList());
    }

    public void create(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleConverter.toEntity(scheduleDto);
        if (scheduleJdbcDao.courseGroupExists(schedule.getCourse().getId(), schedule.getGroup().getId()) && scheduleJdbcDao.isScheduleTimeCorrectForCreate(schedule)) {
            scheduleJdbcDao.save(schedule);
        }
    }

    public void delete(int id) {
        scheduleJdbcDao.delete(id);
    }

    public void edit(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleConverter.toEntity(scheduleDto);
        if (scheduleJdbcDao.isScheduleTimeCorrectForEdit(schedule)) {
            scheduleJdbcDao.update(schedule);
        }
    }

    public ScheduleDto findById(int id) {
        Schedule schedule = scheduleJdbcDao.findById(id);
        return scheduleConverter.toDto(schedule);
    }
}
