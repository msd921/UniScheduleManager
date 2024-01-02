package com.spring.dto;

import com.spring.model.Course;
import com.spring.model.Group;
import com.spring.model.Teacher;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ScheduleDto {

    @Min(1)
    private Integer id;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime date;



    private Group group;

    private Teacher teacher;

    private Course course;
}
