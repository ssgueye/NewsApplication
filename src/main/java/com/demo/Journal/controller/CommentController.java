package com.demo.Journal.controller;

import com.demo.Journal.model.Comment;
import com.demo.Journal.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("journal/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("article/{id}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByArticleId(@PathVariable("id") Long id){
        return ResponseEntity.ok(commentService.getAllCommentsByArticleId(id));
    }

    @PostMapping("article/{id}/comment")
    public ResponseEntity<Comment> createAComment(@Validated @PathVariable("id") Long id,
                                                  @RequestBody Comment comment){
        return ResponseEntity.ok(commentService.createComment(id, comment));
    }

    @PutMapping("article/comment/{id_comment}")
    public ResponseEntity<Comment> updateComment(@Validated @PathVariable("id_comment") Long id_comment,
                                                 @RequestBody Comment comment){
        return ResponseEntity.ok(commentService.updateComment(id_comment, comment));
    }

    @DeleteMapping("article/comment/{id}")
    public ResponseEntity deleteComment(@Validated @PathVariable("id") Long id){
        commentService.deleteComment(id);

        return ResponseEntity.ok().build();
    }
}
