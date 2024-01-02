package com.spring.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Schedule {

    private Integer id;

    private LocalDateTime date;

    private Group group;

    private Teacher teacher;

    private Course course;
}
