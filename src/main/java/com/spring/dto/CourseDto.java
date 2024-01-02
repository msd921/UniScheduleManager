package com.spring.dto;

import lombok.Data;


import javax.validation.constraints.*;
import java.util.List;

@Data
public class CourseDto {
    @Min(1)
    private Integer id;

    @Size(min = 2, max = 50)
    private String name;

    @Size(min = 5, max = 300)
    private String description;

    private List<GroupDto> groups;

    @NotNull
    private TeacherDto teacher;

}
