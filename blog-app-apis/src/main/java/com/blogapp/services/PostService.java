package com.blogapp.services;

import com.blogapp.entities.Post;
import com.blogapp.payloads.PostDto;
import com.blogapp.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    //get all posts
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get specific post
    PostDto getPostById(Integer postId);

    //get all posts by category
    PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get all posts by user
    PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //search posts based on keyword
    List<PostDto> searchPosts(String keyword);
}
