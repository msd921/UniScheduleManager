package com.spring.converter;

import com.spring.dto.StudentDto;
import com.spring.dto.TeacherDto;
import com.spring.model.Student;
import com.spring.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

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


    @PostConstruct
    public void runAfterObjectCreated(){
        modelMapper.createTypeMap(Student.class, StudentDto.class)
                .addMapping(src -> src.getGroup().getName(), StudentDto::setGroupName)
                .addMapping(src -> src.getGroup().getId(), StudentDto::setGroupId);

        modelMapper.createTypeMap(StudentDto.class, Student.class)
                .addMapping(StudentDto::getGroupName, (dest, value) -> dest.getGroup().setName((String) value))
                .addMapping(StudentDto::getGroupId, (dest, value) -> dest.getGroup().setId((Integer) value));
    }
}
