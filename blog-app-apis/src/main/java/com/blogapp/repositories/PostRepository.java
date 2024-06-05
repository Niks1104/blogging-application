package com.blogapp.repositories;

import com.blogapp.entities.Category;
import com.blogapp.entities.Post;
import com.blogapp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Page<Post> findAllByUser(User user, Pageable page);
    Page<Post> findAllByCategory(Category category, Pageable page);

    //in backend containing method will perform like search
    List<Post> findByTitleContaining(String title);

}
