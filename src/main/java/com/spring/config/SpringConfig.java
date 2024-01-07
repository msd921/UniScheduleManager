package com.spring.config;

import com.spring.dto.GroupDto;
import com.spring.dto.ScheduleDto;
import com.spring.dto.StudentDto;
import com.spring.dto.TeacherDto;
import com.spring.model.Group;
import com.spring.model.Schedule;
import com.spring.model.Student;
import com.spring.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@SpringBootApplication
@RequiredArgsConstructor
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    private final DataSource dataSource;



    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Student.class, StudentDto.class)
                .addMapping(src -> src.getGroup().getName(), StudentDto::setGroupName)
                        .addMapping(src -> src.getGroup().getId(), StudentDto::setGroupId);

        modelMapper.createTypeMap(StudentDto.class, Student.class)
                .addMapping(StudentDto::getGroupName, (dest, value) -> dest.getGroup().setName((String) value))
                        .addMapping(StudentDto::getGroupId, (dest, value) -> dest.getGroup().setId((Integer) value));

        modelMapper.createTypeMap(Group.class, GroupDto.class)
                .addMapping(Group::getStudents, GroupDto::setStudents);


        return modelMapper;
    }


}
