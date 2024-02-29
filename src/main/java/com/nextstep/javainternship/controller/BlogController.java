package com.nextstep.javainternship.controller;

import com.nextstep.javainternship.entity.Blog;
import com.nextstep.javainternship.entity.Comment;
import com.nextstep.javainternship.service.BlogService;
import com.nextstep.javainternship.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    CommentService commentService;

    @PostMapping("/addBlog")
    public ResponseEntity<Blog> addBlog(@Valid @RequestBody Blog blog) {
        Blog savedBlog = blogService.saveBlog(blog);
        return ResponseEntity.ok(savedBlog);
    }

    @PutMapping("/updateBlog/{id}")
    public ResponseEntity<String> updateBlog(@Valid @RequestBody Blog blog, @PathVariable Integer id) {
        try {
            blogService.updateBlog(blog, id);
            return ResponseEntity.ok("Blog updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update blog");
        }
    }

    @DeleteMapping("deleteBlog/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Integer id) {
        try {
            blogService.deleteBlog(id);
            return ResponseEntity.ok("Blog deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete blog");
        }
    }

//    @PostMapping("/{blogId}/comments")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Comment addCommentToBlog(@RequestBody Comment comment, @PathVariable int id) {
//        return commentService.saveComment(comment, id);
//    }
//
//    // Get All Comments for a Blog
//    @GetMapping("/{blogId}/comments")
//    public List<Comment> getCommentsForBlog(@PathVariable int blogId) {
//        return commentService.getCommentsForBlog(blogId);
//    }
}
