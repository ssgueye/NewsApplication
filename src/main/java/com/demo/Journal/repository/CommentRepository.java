package com.demo.Journal.repository;

import com.demo.Journal.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteAllByArticleId(Long id);
    List<Comment> getAllByArticleId(Long id);
}
