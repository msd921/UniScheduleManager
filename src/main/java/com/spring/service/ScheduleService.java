package com.spring.service;

import com.spring.converter.ScheduleConverter;
import com.spring.dao.ScheduleRepository;
import com.spring.dto.ScheduleDto;
import com.spring.model.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final ScheduleConverter scheduleConverter;

    public List<ScheduleDto> getAll() {
        return scheduleRepository.findAll().stream()
                .map(scheduleConverter::toDto)
                .collect(Collectors.toList());
    }

    public void create(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleConverter.toEntity(scheduleDto);
        if (scheduleRepository.courseGroupExists(schedule.getCourse().getId(), schedule.getGroup().getId())) {
            scheduleRepository.save(schedule);
        }
    }

    public void delete(int id) {
        scheduleRepository.deleteById(id);
    }

    public void edit(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleConverter.toEntity(scheduleDto);
        if (scheduleRepository.courseGroupExists(schedule.getCourse().getId(), schedule.getGroup().getId())) {
            scheduleRepository.save(schedule);
        }
    }

    public ScheduleDto findById(int id) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);
        return scheduleConverter.toDto(schedule);
    }
}
