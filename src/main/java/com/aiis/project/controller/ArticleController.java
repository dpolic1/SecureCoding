package com.aiis.project.controller;


import com.aiis.project.dto.ArticleDTO;
import com.aiis.project.request.ArticleRequest;
import com.aiis.project.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping()
    public List<ArticleDTO> getAllArticles(){
        return articleService.findAll();
    }

    @GetMapping("/unsafe/{code}")
    public List<ArticleDTO> getArticleByCode(@PathVariable String code){
        return articleService.findByCodeUnsafe(code);
    }

    @GetMapping("/id/{id}")
    public ArticleDTO findArticleById(@PathVariable Long id){
        return articleService.findById(id);
    }

    @GetMapping("/code/{code}")
    public ArticleDTO findArticleByCode(@PathVariable String code){
        return articleService.findByCode(code);
    }

    @PostMapping()
    public void createArticle(@RequestBody @Valid ArticleRequest articleRequest){
        articleService.create(articleRequest);
    }

    @PutMapping("/id/{id}")
    public void updateArticle(@PathVariable Long id, @RequestBody @Valid ArticleRequest articleRequest){
        articleService.updateById(id, articleRequest);
    }

    @PutMapping("/code/{code}")
    public void updateArticle(@PathVariable String code, @RequestBody @Valid ArticleRequest articleRequest){
        articleService.updateByCode(code, articleRequest);
    }

    @DeleteMapping("/id/{id}")
    public void deleteArticleById(@PathVariable Long id){
        articleService.deleteById(id);
    }

    @DeleteMapping("/code/{code}")
    public void deleteArticleByCode(@PathVariable String code){
        articleService.deleteByCode(code);
    }
}
