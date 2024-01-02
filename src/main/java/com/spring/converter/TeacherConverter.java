package com.spring.converter;

import com.spring.dto.TeacherDto;
import com.spring.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherConverter {

    private final ModelMapper modelMapper;

    public TeacherDto toDto(Teacher teacher) {
        return modelMapper.map(teacher, TeacherDto.class);
    }

    public Teacher toEntity(TeacherDto teacherDto) {
        return modelMapper.map(teacherDto, Teacher.class);
    }

}
