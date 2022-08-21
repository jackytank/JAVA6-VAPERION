package com.edu.java6assm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping({ "/", "/home/index" })
    public ModelAndView home(ModelMap model, @RequestParam(required = false) String message) {
        model.addAttribute("message", message);
        return new ModelAndView("redirect:/product/list", model);
    }

    @GetMapping({ "/admin", "/admin/home/index" })
    public String admin() {
        return "redirect:/assets/admin/index.html";
    }
}
