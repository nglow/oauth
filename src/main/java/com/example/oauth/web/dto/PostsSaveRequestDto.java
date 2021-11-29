package com.example.oauth.web.dto;

import com.example.oauth.domain.posts.Posts;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    public static PostsSaveRequestDto of(String title, String content, String author) {
        var instance = new PostsSaveRequestDto();

        instance.title = title;
        instance.content = content;
        instance.author = author;

        return instance;
    }

    public Posts toEntity() {
        return Posts.of(title, content, author);
    }
}
