package com.spring.converter;

import com.spring.dto.CourseDto;
import com.spring.dto.ScheduleDto;
import com.spring.model.Course;
import com.spring.model.Schedule;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleConverter {

    private final ModelMapper modelMapper;

    public ScheduleDto toDto(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleDto.class);
    }

    public Schedule toEntity(ScheduleDto scheduleDto) {
        return modelMapper.map(scheduleDto, Schedule.class);
    }
}
