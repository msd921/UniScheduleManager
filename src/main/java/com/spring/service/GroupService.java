package com.spring.service;

import com.spring.converter.GroupConverter;
import com.spring.converter.ScheduleConverter;
import com.spring.dao.CourseRepository;
import com.spring.dao.GroupRepository;
import com.spring.dao.ScheduleRepository;
import com.spring.dto.GroupDto;
import com.spring.dto.ScheduleDto;
import com.spring.model.Course;
import com.spring.model.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final ScheduleRepository scheduleRepository;

    private final GroupConverter groupConverter;

    private final ScheduleConverter scheduleConverter;

    public List<GroupDto> getAll() {
        return groupRepository.findAll().stream()
                .map(groupConverter::toDto)
                .collect(Collectors.toList());
    }

    public void create(GroupDto groupDto) {
        Group group = groupConverter.toEntity(groupDto);
        groupRepository.save(group);
    }

    public void delete(int id) {
        groupRepository.deleteById(id);
    }

    public GroupDto findById(int id) {
        Group group = groupRepository.findById(id).orElse(null);
        return groupConverter.toDto(group);
    }

    public void edit(GroupDto groupDto) {
        Group group = groupConverter.toEntity(groupDto);
        groupRepository.save(group);
    }

    public List<ScheduleDto> getSchedule(int groupId) {
        List<ScheduleDto> schedules = groupRepository.findSchedulesByGroupId(groupId).stream()
                .map(scheduleConverter::toDto)
                .collect(Collectors.toList());
        if(schedules.isEmpty()){
            throw new RuntimeException("Schedules is empty");
        }
        return schedules;
    }

    public void addCourse(int courseId, int groupId){
        if (courseId != 0 && !scheduleRepository.courseGroupExists(courseId,groupId)) {
            Course course = courseRepository.findById(courseId).orElse(null);
            Group group = groupRepository.findById(groupId).orElse(null);
            if (course != null && group != null) {
                course.getGroups().add(group);
                courseRepository.save(course);
            }
        }
    }

}
