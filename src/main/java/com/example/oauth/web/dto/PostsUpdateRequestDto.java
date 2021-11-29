package com.example.oauth.web.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PostsUpdateRequestDto {

    private String title;
    private String content;

    public static PostsUpdateRequestDto of(String title, String content) {
        var instance = new PostsUpdateRequestDto();
        instance.title = title;
        instance.content = content;

        return instance;
    }
}
