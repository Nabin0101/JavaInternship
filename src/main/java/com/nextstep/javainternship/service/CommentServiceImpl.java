package com.nextstep.javainternship.service;

import com.nextstep.javainternship.entity.Comment;
import com.nextstep.javainternship.entity.User;
import com.nextstep.javainternship.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements  CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void updateComment(Comment comment, int id) {
        Optional<Comment> commentDetails = commentRepository.findById(id);
        if (commentDetails.isPresent()) {
            Comment commentUpdate = commentDetails.get();
            commentUpdate.setContent(comment.getContent());
            commentRepository.save(commentUpdate);
        }
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment getComment(int id) {
        Optional<Comment> commentFound =commentRepository.findById(id);
        return commentFound.orElse(null);
    }

    @Override
    public Comment saveComment(Comment comment, int id) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsForBlog(int id) {
        return commentRepository.findByBlogId(id);
    }
}

