package com.spring.dao;

import com.spring.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query("SELECT COUNT(c) > 0 FROM Course c JOIN c.groups g WHERE c.id = :courseId AND g.id = :groupId")
    boolean courseGroupExists(@Param("courseId") int courseId,@Param("groupId") int groupId);
}
