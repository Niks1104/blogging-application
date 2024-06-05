package com.blogapp.services.impl;

import com.blogapp.entities.Category;
import com.blogapp.entities.Post;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.PostDto;
import com.blogapp.payloads.PostResponse;
import com.blogapp.repositories.CategoryRepository;
import com.blogapp.repositories.PostRepository;
import com.blogapp.repositories.UserRepository;
import com.blogapp.services.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = userRepository.findById(userId)
                    .orElseThrow(() ->new ResourceNotFoundException("User", "userId", userId));
        Category category = categoryRepository.findById(categoryId)
                            .orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));

        Post post = dtoToPost(postDto);
        post.setImageName("default.png");
        post.setPostDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost = postRepository.save(post);

        return postToDto(savedPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post","postId", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = postRepository.save(post);
        return postToDto(updatedPost);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post","postId", postId));

        postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        //to create sort object according to given propeerties
        Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable page = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = postRepository.findAll(page);
        List<Post> posts = pagePost.getContent();

        List<PostDto> postDtos = posts.stream().map(post -> postToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = PostResponse.of().content(postDtos)
                                    .pageNumber(pagePost.getNumber())
                                    .pageSize(pagePost.getSize())
                                    .totalElements(pagePost.getTotalElements())
                                    .totalPages(pagePost.getTotalPages())
                                    .lastPage(pagePost.isLast()).build();

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));

        return postToDto(post);
    }

    @Override
    public PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));

        Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable page = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = postRepository.findAllByCategory(category, page);
        List<Post> posts = pagePost.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> postToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = PostResponse.of()
                                    .content(postDtos)
                                    .pageNumber(pagePost.getNumber())
                                    .pageSize(pagePost.getSize())
                                    .totalElements(pagePost.getTotalElements())
                                    .totalPages(pagePost.getTotalPages())
                                    .lastPage(pagePost.isLast()).build();

        return postResponse;
    }

    @Override
    public PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));

        Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable page = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = postRepository.findAllByUser(user, page);
        List<Post> posts = pagePost.getContent();

        List<PostDto> postDtos = posts.stream().map(post -> postToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = PostResponse.of()
                                    .content(postDtos)
                                    .pageNumber(pagePost.getNumber())
                                    .pageSize(pagePost.getSize())
                                    .totalElements(pagePost.getTotalElements())
                                    .totalPages(pagePost.getTotalPages())
                                    .lastPage(pagePost.isLast()).build();
        return postResponse;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = postRepository.findByTitleContaining(keyword);

        List<PostDto> postDtos = posts.stream().map(post -> postToDto(post)).collect(Collectors.toList());
        return postDtos;
    }

    public PostDto postToDto(Post post){
        return modelMapper.map(post, PostDto.class);
    }

    public Post dtoToPost(PostDto postDto){
        return modelMapper.map(postDto, Post.class);
    }
}
