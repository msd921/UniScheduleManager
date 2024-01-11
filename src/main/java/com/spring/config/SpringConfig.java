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
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
@RequiredArgsConstructor
public class SpringConfig implements WebMvcConfigurer {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
