package com.nextstep.javainternship.repository;

import com.nextstep.javainternship.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
