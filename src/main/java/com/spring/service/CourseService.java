package com.spring.service;

import com.spring.converter.CourseConverter;
import com.spring.dao.CourseRepository;
import com.spring.dto.CourseDto;
import com.spring.model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final CourseConverter courseConverter;

    public List<CourseDto> getAll() {
        return courseRepository.findAll().stream()
                .map(courseConverter::toDto)
                .collect(Collectors.toList());
    }

    public void create(CourseDto courseDto) {
        Course course = courseConverter.toEntity(courseDto);
        courseRepository.save(course);
    }

    public void delete(int id) {
        courseRepository.deleteById(id);
    }

    public CourseDto findById(int id) {
        Course course = courseRepository.findById(id).orElse(null);
        return courseConverter.toDto(course);
    }

    public void edit(CourseDto courseDto) {
        Course course = courseConverter.toEntity(courseDto);
        courseRepository.save(course);
    }
}
