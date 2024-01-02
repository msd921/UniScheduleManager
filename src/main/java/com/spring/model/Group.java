package com.spring.model;

import lombok.Data;

import java.util.List;

@Data
public class Group {

    private Integer id;
    private String name;
    private List<Student> students;
    private List<Course> courses;

}
