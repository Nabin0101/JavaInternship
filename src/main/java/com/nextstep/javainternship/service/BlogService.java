package com.nextstep.javainternship.service;

import com.nextstep.javainternship.entity.Blog;


public interface BlogService {
    public Blog saveBlog(Blog blog);
    public void updateBlog(Blog blog , int id);
    public void deleteBlog(int id);
    public Blog getBlog(int id);

}
