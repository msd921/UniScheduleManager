package com.spring.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class GroupDto {


    @Min(1)
    private Integer id;

    @Size(min = 2, max = 50)
    private String name;
    private List<StudentDto> students;
    private List<CourseDto> courses;
}
