package com.spring.dao;

import com.spring.model.Schedule;
import com.spring.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeacherJdbcDao {

    private final TeacherRepository teacherRepository;

    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void update(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void delete(int id) {
        teacherRepository.deleteById(id);
    }

    public Teacher findById(int id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public List<Schedule> getScheduleByTeacherId(int id){
        return teacherRepository.findSchedulesByTeacherId(id);
    }

}
