package com.spring.controller;

import com.spring.dto.CourseDto;
import com.spring.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;

@Controller
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/")
    public String getCourses(Model model){
        model.addAttribute("courses",courseService.getAll());
        return "course/courses";
    }


    @GetMapping("/create")
    public String create(){
        return "course/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute CourseDto courseDto, BindingResult result){
        if(result.hasErrors()){
            return "course/create";
        }
        courseService.create(courseDto);
        return "redirect:/course/";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam("id") int courseId) {
        courseService.delete(courseId);
        return "redirect:/course/";
    }

    @GetMapping("/courses-edit")
    public String edit(@RequestParam("id") int courseId, Model model) {
        CourseDto courseDto = courseService.findById(courseId);
        model.addAttribute("course", courseDto);
        return "course/courses-edit";
    }

    @PostMapping("/courses-edit")
    public String edit(@Valid @ModelAttribute CourseDto courseDto, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/course/courses-edit?id=" + courseDto.getId();
        }
        courseService.edit(courseDto);
        return "redirect:/course/";
    }


}
