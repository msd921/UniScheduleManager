package com.spring.converter;

import com.spring.dto.StudentDto;
import com.spring.dto.TeacherDto;
import com.spring.model.Student;
import com.spring.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentConverter {

    private final ModelMapper modelMapper;

    public StudentDto toDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

    public Student toEntity(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }

}
