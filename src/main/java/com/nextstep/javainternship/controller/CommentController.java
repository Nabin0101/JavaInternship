package com.nextstep.javainternship.controller;

import com.nextstep.javainternship.entity.Comment;
import com.nextstep.javainternship.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/addComment")
    public ResponseEntity<String> addComment(@Valid @RequestBody Comment comment) {
        try {
             commentService.saveComment(comment);
            return ResponseEntity.ok("Comment saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save comment");
        }
    }

    @GetMapping("/getComment/{id}")
    public ResponseEntity<Comment> getCommentDetails(@PathVariable("id") int id) {
        try {
            Comment comment = commentService.getComment(id);
            if (comment != null) {
                return ResponseEntity.ok(comment);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

    }

    @PutMapping("updateComment/{id}")
    public ResponseEntity<String> updateComment(@Valid @RequestBody Comment comment, @PathVariable Integer id) {
        try {
            commentService.updateComment(comment, id);
            return ResponseEntity.ok("Comment updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update comment");
        }
    }

    @DeleteMapping("deleteComment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer id) {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.ok("Comment deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete comment");
        }
    }
}
