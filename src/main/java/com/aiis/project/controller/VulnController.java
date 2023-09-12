package com.aiis.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Provider;

@Controller
public class VulnController {
    @GetMapping("/xss")
    public String executeXSS(@RequestParam(name = "param") String param, Model model) {
        model.addAttribute("param", param);
        return "xss";
    }

    @RequestMapping(value = "/csrf", method = {RequestMethod.GET, RequestMethod.POST})
    public String executeCSRF(@ModelAttribute Provider formModel, Model model) {
        model.addAttribute("formModel", formModel);
        return "csrf";
    }

}
