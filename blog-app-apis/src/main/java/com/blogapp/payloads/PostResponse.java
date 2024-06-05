package com.blogapp.payloads;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class PostResponse {

    private List<PostDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private boolean lastPage;
}
