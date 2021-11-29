package com.example.oauth.web.dto;

import com.example.oauth.domain.posts.Posts;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PostsListResponseDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public static PostsListResponseDto from(Posts entity) {
        var instance  = new PostsListResponseDto();
        instance.id = entity.getId();
        instance.title = entity.getTitle();
        instance.author = entity.getAuthor();
        instance.modifiedDate = entity.getModifiedDate();

        return instance;
    }
}
