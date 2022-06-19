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
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    public List<Comment> getAllCommentsByArticleId(Long id){
        return commentRepository.getAllByArticleId(id);
    }

    public Comment createComment(Long article_id, Comment comment){
        Article article = articleRepository.findById(article_id)
                .orElseThrow(()-> new ResourceNotFound("Article Not found"));

        comment.setArticle(article);
        Date date = new Date();
        DateFormat dateFormat = DateFormat
                .getDateTimeInstance(
                        DateFormat.SHORT,
                        DateFormat.SHORT
                );
        comment.setPublishedDate(dateFormat.format(date));

        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id_comment, Comment comment){
        Comment comment1 = commentRepository.findById(id_comment)
                .orElseThrow(()-> new ResourceNotFound("Article Not found"));



        comment1.setContent(comment.getContent());
        comment1.setAuthor(comment.getAuthor());
        comment1.setPublishedDate(comment.getPublishedDate());
        Date date = new Date();
        DateFormat dateFormat = DateFormat
                .getDateTimeInstance(
                        DateFormat.SHORT,
                        DateFormat.SHORT
                );
        comment1.setPublishedDate(dateFormat.format(date));

        return commentRepository.save(comment1);
    }

    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }
}
