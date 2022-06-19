package com.demo.Journal.service;

import com.demo.Journal.exceptions.ResourceNotFound;
import com.demo.Journal.model.Article;
import com.demo.Journal.model.Comment;
import com.demo.Journal.repository.ArticleRepository;
import com.demo.Journal.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public ArticleService(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id){
        return articleRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFound("Article Not found"));
    }

    public Article createArticle(Article article){
        Date date = new Date();
        DateFormat dateFormat = DateFormat
                .getDateTimeInstance(
                        DateFormat.SHORT,
                        DateFormat.SHORT
                );
        article.setPublishedDate(dateFormat.format(date));

        return articleRepository.save(article);
    }

    public Article updateArticleById(Long id, Article article){
        Article article1 = articleRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFound("Article Not found"));

        article1.setTitle(article.getTitle());
        article1.setContent(article.getContent());
        article1.setPublishedDate(article.getPublishedDate());
        article1.setAuthor(article.getAuthor());
        return articleRepository.save(article1);
    }

    public void deleteArticleById(Long id){
        commentRepository.deleteAllByArticleId(id);
        articleRepository.deleteById(id);
    }
}
