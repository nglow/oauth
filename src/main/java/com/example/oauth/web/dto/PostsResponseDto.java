package com.example.oauth.web.dto;

import com.example.oauth.domain.posts.Posts;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public static PostsResponseDto from(Posts entity) {
        var instance = new PostsResponseDto();
        instance.id = entity.getId();
        instance.title = entity.getTitle();
        instance.content = entity.getContent();
        instance.author = entity.getAuthor();

        return instance;
    }
}
