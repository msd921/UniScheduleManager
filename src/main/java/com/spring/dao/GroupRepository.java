package com.spring.dao;

import com.spring.model.Group;
import com.spring.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    Optional<Group> findByName(String name);


    @Query("SELECT s FROM Schedule s JOIN FETCH s.course c WHERE s.group.id = :groupId")
    List<Schedule> findSchedulesByGroupId(@Param("groupId") int groupId);
}
