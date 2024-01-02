package com.spring.service;

import com.spring.converter.GroupConverter;
import com.spring.converter.ScheduleConverter;
import com.spring.dao.GroupJdbcDao;
import com.spring.dao.ScheduleJdbcDao;
import com.spring.dto.GroupDto;
import com.spring.dto.ScheduleDto;
import com.spring.model.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupJdbcDao groupJdbcDao;

    private final GroupConverter groupConverter;
    private final ScheduleJdbcDao scheduleJdbcDao;

    private final ScheduleConverter scheduleConverter;

    public List<GroupDto> getAll() {
        return groupJdbcDao.getAll().stream()
                .map(groupConverter::toDto)
                .collect(Collectors.toList());
    }

    public void create(GroupDto groupDto) {
        Group group = groupConverter.toEntity(groupDto);
        groupJdbcDao.save(group);
    }

    public void delete(int id) {
        groupJdbcDao.delete(id);
    }

    public GroupDto findById(int id) {
        Group group = groupJdbcDao.findById(id);
        group.setStudents(groupJdbcDao.getStudentsInGroup(id));
        group.setCourses(groupJdbcDao.getCoursesForGroup(id));
        return groupConverter.toDto(group);
    }

    public void edit(GroupDto groupDto) {
        Group group = groupConverter.toEntity(groupDto);
        groupJdbcDao.update(group);
    }

    public List<ScheduleDto> getSchedule(int groupId) {
        return groupJdbcDao.getScheduleForGroup(groupId).stream()
                .map(scheduleConverter::toDto)
                .collect(Collectors.toList());
    }

    public void addCourse(int courseId, int groupId){
        if (courseId != 0 && !scheduleJdbcDao.courseGroupExists(courseId,groupId)) {
            groupJdbcDao.addGroupToCourse(courseId, groupId);
        }
    }

}
