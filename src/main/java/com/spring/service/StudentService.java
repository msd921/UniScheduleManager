package com.spring.service;

import com.spring.converter.StudentConverter;
import com.spring.dao.GroupRepository;
import com.spring.dao.StudentRepository;
import com.spring.dto.StudentDto;
import com.spring.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final GroupRepository groupRepository;

    private final StudentConverter studentConverter;

    public List<StudentDto> getAll() {
        return studentRepository.findAll().stream()
                .map(studentConverter::toDto)
                .collect(Collectors.toList());
    }

    public void create(StudentDto studentDto) {
        Student student = studentConverter.toEntity(studentDto);
        student.setGroup(groupRepository.findByName(studentDto.getGroupName()));
        studentRepository.save(student);
    }

    public void delete(int id) {
        studentRepository.deleteById(id);
    }

    public StudentDto findById(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        return studentConverter.toDto(student);
    }

    public void edit(StudentDto studentDto) {
        Student student = studentConverter.toEntity(studentDto);
        student.setGroup(groupRepository.findByName(studentDto.getGroupName()));
        studentRepository.save(student);
    }


}
