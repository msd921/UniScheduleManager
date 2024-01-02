package com.spring.service;

import com.spring.converter.StudentConverter;
import com.spring.dao.GroupJdbcDao;
import com.spring.dao.StudentJdbcDao;
import com.spring.dto.StudentDto;
import com.spring.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentJdbcDao studentJdbcDao;

    private final GroupJdbcDao groupJdbcDao;

    private final StudentConverter studentConverter;

    public List<StudentDto> getAll() {
        return studentJdbcDao.getAll().stream()
                .map(studentConverter::toDto)
                .collect(Collectors.toList());
    }

    public void create(StudentDto studentDto) {
        Student student = studentConverter.toEntity(studentDto);
        student.setGroup(groupJdbcDao.findByName(studentDto.getGroupName()));
        studentJdbcDao.save(student);
    }

    public void delete(int id) {
        studentJdbcDao.delete(id);
    }

    public StudentDto findById(int id) {
        Student student = studentJdbcDao.findById(id);
        return studentConverter.toDto(student);
    }

    public void edit(StudentDto studentDto) {
        Student student = studentConverter.toEntity(studentDto);
        student.setGroup(groupJdbcDao.findByName(studentDto.getGroupName()));
        studentJdbcDao.update(student);
    }


}
