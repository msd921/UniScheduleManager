package com.spring.mapper;

import com.spring.model.Group;
import com.spring.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setSurname(rs.getString("surname"));
        Group group = new Group();
        group.setId(rs.getInt("group_id"));
        group.setName(rs.getString("groupName"));
        student.setGroup(group);
        return student;
    }
}
