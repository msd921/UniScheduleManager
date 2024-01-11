package com.spring.controller;

import com.spring.dto.ScheduleDto;
import com.spring.dto.TeacherDto;
import com.spring.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/")
    public String getTeachers(Model model) {
        model.addAttribute("teachers", teacherService.getAll());
        return "teacher/teachers";
    }

    @GetMapping("/create")
    public String create() {
        return "teacher/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute TeacherDto teacherDto, BindingResult result) {
        if (result.hasErrors()) {
            return "teacher/create";
        }
        teacherService.create(teacherDto);
        return "redirect:/teacher/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int teacherId) {
        teacherService.delete(teacherId);
        return "redirect:/teacher/";
    }

    @GetMapping("/teacher-edit")
    public String edit(@RequestParam("id") int teacherId, Model model) {
        TeacherDto teacherDto = teacherService.findById(teacherId);
        model.addAttribute("teacher", teacherDto);
        return "teacher/teacher-edit";
    }


    @PostMapping("/teacher-edit")
    public String edit(@Valid @ModelAttribute TeacherDto teacherDto, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/teacher/teacher-edit?id=" + teacherDto.getId();
        }
        teacherService.edit(teacherDto);
        return "redirect:/teacher/";
    }

    @GetMapping("/teacher-schedule")
    public String showSchedule(@RequestParam("id") int teacherId, Model model) {
        List<ScheduleDto> schedules = teacherService.getSchedule(teacherId);
        model.addAttribute("schedules", schedules);
        return "teacher/teacher-schedule";
    }

}
