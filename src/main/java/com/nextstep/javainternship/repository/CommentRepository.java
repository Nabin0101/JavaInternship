package com.nextstep.javainternship.repository;

import com.nextstep.javainternship.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByBlogId(int id);
}
