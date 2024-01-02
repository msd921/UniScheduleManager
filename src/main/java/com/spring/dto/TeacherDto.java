package com.spring.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TeacherDto {

    @Min(1)
    private Integer id;

    @Size(min = 2, max = 50)
    private String name;

    @Size(min = 2, max = 50)
    private String surname;
}
