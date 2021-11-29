package com.example.oauth.domain.posts;

import com.example.oauth.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Posts extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    public static Posts of(String title, String content, String author) {
        var posts = new Posts();
        posts.title = title;
        posts.content = content;
        posts.author = author;

        return posts;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
