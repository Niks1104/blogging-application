package com.blogapp.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Integer categoryId;
    @NotEmpty
    @Size(min = 4, message = "Title should be of min 4 characters")
    private String categoryTitle;

    @NotEmpty
    @Size(min = 10, message = "Description should be of more than 10 characters")
    private String categoryDescription;
}
