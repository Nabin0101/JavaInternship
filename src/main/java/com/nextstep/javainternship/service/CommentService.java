package com.nextstep.javainternship.service;

import com.nextstep.javainternship.entity.Blog;
import com.nextstep.javainternship.entity.Comment;

public interface CommentService {
    public Comment saveComment(Comment comment);
    public void updateComment(Comment commnet , int id);
    public void deleteComment(int id);
    public Comment getComment(int id);
}
