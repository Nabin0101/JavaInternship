package com.nextstep.javainternship.service;

import com.nextstep.javainternship.entity.Blog;
import com.nextstep.javainternship.entity.User;
import com.nextstep.javainternship.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void updateBlog(Blog blog, int id) {
        Optional<Blog> blogDetails = blogRepository.findById(id);
        if (blogDetails.isPresent()) {
            Blog blogUpdate =blogDetails.get();
            blogUpdate.setTitle(blog.getTitle());
            blogUpdate.setContent(blog.getContent());
            blogUpdate.setComments(blog.getComments());
            blogUpdate.setThumbnailImage(blog.getThumbnailImage());
            blogRepository.save(blogUpdate);
        }
    }

    @Override
    public void deleteBlog(int id) {
        blogRepository.deleteById(id);

    }

    @Override
    public Blog getBlog(int id) {
        Optional<Blog> blogFound= blogRepository.findById(id);
        return blogFound.orElse(null);
    }
}
