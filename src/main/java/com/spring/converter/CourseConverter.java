package com.spring.converter;

import com.spring.dto.CourseDto;
import com.spring.dto.GroupDto;
import com.spring.model.Course;
import com.spring.model.Group;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseConverter {

    private final ModelMapper modelMapper;

    public CourseDto toDto(Course course) {
        return modelMapper.map(course, CourseDto.class);
    }

    public Course toEntity(CourseDto courseDto) {
        return modelMapper.map(courseDto, Course.class);
    }
}
