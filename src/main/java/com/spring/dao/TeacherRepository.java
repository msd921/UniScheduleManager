package com.spring.dao;

import com.spring.model.Schedule;
import com.spring.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query("SELECT s FROM Schedule s JOIN FETCH s.course c WHERE c.teacher.id = :teacherId")
    List<Schedule> findSchedulesByTeacherId(@Param("teacherId") Integer teacherId);
}
