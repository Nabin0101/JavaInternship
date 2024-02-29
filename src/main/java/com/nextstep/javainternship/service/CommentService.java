package com.nextstep.javainternship.service;

import com.nextstep.javainternship.entity.Blog;
import com.nextstep.javainternship.entity.Comment;

import java.util.List;

public interface CommentService {
    public Comment saveComment(Comment comment);
    public void updateComment(Comment commnet , int id);
    public void deleteComment(int id);
    public Comment getComment(int id);
    Comment saveComment(Comment comment, int id);
    List<Comment> getCommentsForBlog(int id);
}
