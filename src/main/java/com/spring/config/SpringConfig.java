package com.spring.config;

import com.spring.dto.GroupDto;
import com.spring.dto.StudentDto;
import com.spring.model.Group;
import com.spring.model.Student;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Objects;

@Configuration
@ComponentScan("com.spring")
@EnableWebMvc
@RequiredArgsConstructor
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    private final DataSource dataSource;

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/templates/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

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

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() throws IOException {
        PropertySourcesPlaceholderConfigurer config = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        config.setProperties(Objects.requireNonNull(yaml.getObject()));
        return config;
    }

}
