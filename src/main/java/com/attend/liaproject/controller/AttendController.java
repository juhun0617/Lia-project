package com.attend.liaproject.controller;

import com.attend.liaproject.entity.Attend;
import com.attend.liaproject.entity.Member;
import com.attend.liaproject.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AttendController {
    @Autowired
    private AttendService attendService;

    @ResponseBody
    @PostMapping("/attendapi")
    public void attendapi(Attend attend) {
        attendService.attendsave(attend);
    }

    @GetMapping("/teacher/member/attendlist")
    public String readAttenders(Model model) {
        List<Member> members = attendService.readAttenders();
        model.addAttribute("list", members);
        return "attendlist";
    }

    @GetMapping("/teacher/member/notattendlist")
    public String read_not_Attenders(Model model) {
        List<Member> members = attendService.read_Not_Attenders();
        model.addAttribute("list", members);
        return "attendlist";
    }


}
