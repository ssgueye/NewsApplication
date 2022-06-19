package com.demo.Journal.controller;

import com.demo.Journal.model.Article;
import com.demo.Journal.repository.ArticleRepository;
import com.demo.Journal.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("journal/api")
public class ArticleController {

    public final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping("articles")
    public ResponseEntity<List<Article>> getAllArticles(){
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    @GetMapping("articles/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id") Long id){
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PostMapping("article")
    public ResponseEntity<Article> createArticle(@RequestBody Article article){
        return ResponseEntity.ok(articleService.createArticle(article));
    }

    @PutMapping("article/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") Long id,
                                                 @RequestBody Article article){
        return ResponseEntity.ok(articleService.updateArticleById(id, article));
    }

    @DeleteMapping("article/{id}")
    public ResponseEntity deleteArticleById(@PathVariable("id") Long id){
        articleService.deleteArticleById(id);
        return ResponseEntity.ok().build();
    }
}
