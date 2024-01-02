package com.spring.model;

import lombok.Data;

import java.util.List;

@Data
public class Course {

    private Integer id;

    private String name;

    private String description;

    private List<Group> groups;

    private Teacher teacher;
}
