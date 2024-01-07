package com.spring.dao;

import com.spring.model.Course;
import com.spring.model.Group;
import com.spring.model.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupJdbcDao {

    private final GroupRepository groupRepository;

    private final CourseRepository courseRepository;

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    public void save(Group group) {
        groupRepository.save(group);
    }

    public void update(Group group) {
        groupRepository.save(group);
    }

    public void delete(int id) {
        groupRepository.deleteById(id);
    }

    public Group findById(int id) {
        return groupRepository.findById(id).orElse(null);
    }

    public Group findByName(String name) {
        return groupRepository.findByName(name).orElse(null);
    }


    public void addGroupToCourse(int courseId, int groupId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        Group group = groupRepository.findById(groupId).orElse(null);

        if (course != null && group != null) {
            course.getGroups().add(group);
            courseRepository.save(course);
        }
    }

    public List<Schedule> findScheduleByGroupId(int groupId){
        return groupRepository.findSchedulesByGroupId(groupId);
    }



}
