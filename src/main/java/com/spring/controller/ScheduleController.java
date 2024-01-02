package com.spring.controller;

import com.spring.dto.ScheduleDto;
import com.spring.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/")
    public String getSchedules(Model model){
        model.addAttribute("schedules",scheduleService.getAll());
        return "schedule/schedule";
    }


    @GetMapping("/create")
    public String create(){
        return "schedule/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute ScheduleDto scheduleDto, BindingResult result){
        if(result.hasErrors()){
            return "schedule/create";
        }
        scheduleService.create(scheduleDto);
        return "redirect:/schedule/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int scheduleId) {
        scheduleService.delete(scheduleId);
        return "redirect:/schedule/";
    }

    @GetMapping("/schedule-edit")
    public String edit(@RequestParam("id") int scheduleId, Model model) {
        ScheduleDto scheduleDto = scheduleService.findById(scheduleId);
        model.addAttribute("schedule", scheduleDto);
        return "schedule/schedule-edit";
    }

    @PostMapping("/schedule-edit")
    public String edit(@Valid @ModelAttribute ScheduleDto scheduleDto, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/schedule/schedule-edit?id=" + scheduleDto.getId();
        }
        scheduleService.edit(scheduleDto);
        return "redirect:/schedule/";
    }

}
