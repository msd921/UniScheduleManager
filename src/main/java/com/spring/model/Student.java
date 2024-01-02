package com.spring.model;

import lombok.Data;

@Data
public class Student {
    private Integer id;
    private String name;
    private String surname;
    private Group group;
}
