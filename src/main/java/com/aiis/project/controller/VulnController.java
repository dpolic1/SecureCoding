package com.aiis.project.controller;

import com.aiis.project.request.ArticleRequest;
import com.aiis.project.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Provider;

@RestController
@RequestMapping("/vuln")
public class VulnController {

    private final ArticleService articleService;

    public VulnController(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping("/xss")
    public String executeXSS(@RequestParam(name = "param") String param, ArticleRequest articleRequest, Model model) {
        model.addAttribute("param", param);
        articleService.create(articleRequest);
        return "xss";
    }

    @RequestMapping(value = "/csrf", method = {RequestMethod.GET, RequestMethod.POST})
    public String executeCSRF(@ModelAttribute Provider formModel, Model model) {
        model.addAttribute("formModel", formModel);
        return "csrf";
    }

}
