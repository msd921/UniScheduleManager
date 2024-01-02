package com.spring.controller;

import com.spring.dto.CourseDto;
import com.spring.dto.GroupDto;
import com.spring.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/")
    public String getGroups(Model model) {
        model.addAttribute("groups", groupService.getAll());
        return "group/groups";
    }

    @GetMapping("/create")
    public String create() {
        return "group/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute GroupDto groupDto, BindingResult result) {
        if (result.hasErrors()) {
            return "group/create";
        }
        groupService.create(groupDto);
        return "redirect:/group/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int groupId) {
        groupService.delete(groupId);
        return "redirect:/group/";
    }

    @GetMapping("/groups-edit")
    public String edit(@RequestParam("id") int groupId, Model model) {
        GroupDto groupDto = groupService.findById(groupId);
        model.addAttribute("group", groupDto);
        return "group/groups-edit";
    }

    @PostMapping("/groups-edit")
    public String edit(@Valid @ModelAttribute GroupDto groupDto, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/group/groups-edit?id=" + groupDto.getId();
        }
        groupService.edit(groupDto);
        return "redirect:/group/";
    }

    @GetMapping("/groups-schedule")
    public String showSchedule(@RequestParam("id") int groupId, Model model) {
        model.addAttribute("schedules", groupService.getSchedule(groupId));
        return "group/groups-schedule";
    }

    @GetMapping("/groups-add-course")
    public String addCourse(@RequestParam("id") int groupId, Model model) {
        GroupDto groupDto = groupService.findById(groupId);
        model.addAttribute("group", groupDto);
        return "group/groups-add-course";
    }

    @PostMapping("/groups-add-course")
    public String addCourse(@Valid @ModelAttribute GroupDto groupDto, @RequestParam("courseId") int courseId, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/group/groups-add-course?id=" + groupDto.getId();
        }
        groupService.addCourse(courseId, groupDto.getId());
        return "redirect:/group/";
    }
}
