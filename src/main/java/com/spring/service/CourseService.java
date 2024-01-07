package com.spring.service;

import com.spring.converter.CourseConverter;
import com.spring.dao.CourseJdbcDao;
import com.spring.dto.CourseDto;
import com.spring.model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseJdbcDao courseJdbcDao;

    private final CourseConverter courseConverter;

    public List<CourseDto> getAll() {
        return courseJdbcDao.getAll().stream()
                .map(courseConverter::toDto)
                .collect(Collectors.toList());
    }

    public void create(CourseDto courseDto) {
        Course course = courseConverter.toEntity(courseDto);
        courseJdbcDao.save(course);
    }

    public void delete(int id) {
        courseJdbcDao.delete(id);
    }

    public CourseDto findById(int id) {
        Course course = courseJdbcDao.findById(id);
        //course.setGroups(courseJdbcDao.getGroupsInCourse(id));
        return courseConverter.toDto(course);
    }

    public void edit(CourseDto courseDto) {
        Course course = courseConverter.toEntity(courseDto);
        courseJdbcDao.update(course);
    }
}
