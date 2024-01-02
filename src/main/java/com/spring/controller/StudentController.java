package com.spring.controller;

import com.spring.dto.StudentDto;
import com.spring.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/")
    public String getTeachers(Model model) {
        model.addAttribute("students", studentService.getAll());
        return "student/students";
    }

    @GetMapping("/create")
    public String create() {
        return "student/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute StudentDto studentDto, BindingResult result) {
        if (result.hasErrors()) {
            return "student/create";
        }
        studentService.create(studentDto);
        return "redirect:/student/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int studentId) {
        studentService.delete(studentId);
        return "redirect:/student/";
    }

    @GetMapping("/students-edit")
    public String edit(@RequestParam("id") int studentId, Model model) {
        StudentDto studentDto = studentService.findById(studentId);
        model.addAttribute("student", studentDto);
        return "student/students-edit";
    }

    @PostMapping("/students-edit")
    public String edit(@Valid @ModelAttribute StudentDto studentDto, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/student/students-edit?id=" + studentDto.getId();
        }
        studentService.edit(studentDto);
        return "redirect:/student/";
    }
}
