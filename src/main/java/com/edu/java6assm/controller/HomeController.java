package com.edu.java6assm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @GetMapping({ "/", "/home/index" })
    public String home(ModelMap model, @RequestParam(required = false) String message, RedirectAttributes redirAttrs) {
        redirAttrs.addFlashAttribute("message", message);
        model.addAttribute("message", message);
        return "redirect:/product/list";
    }

    @GetMapping({ "/admin", "/admin/home/index" })
    public String admin() {
        return "redirect:/assets/admin/index.html";
    }
}
