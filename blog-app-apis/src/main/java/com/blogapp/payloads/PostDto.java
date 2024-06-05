package com.blogapp.payloads;

import com.blogapp.entities.Category;
import com.blogapp.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    @NotEmpty
    @Size(max=200, message="title should not be more than 200 characters")
    private String title;
    @NotEmpty
    @Size(max=1000, message="more than 1000 characters are not allowed")
    private String content;
    private String imageName;
    private Date postDate;
    private UserDto user;
    private CategoryDto category;
}
