package com.example.oauth.domain.posts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    @DisplayName("게시글 저장_불러오기")
    public void testSavePosts() {
        // Given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.of(title, content, "nglow90@clouflake.com"));

        // When
        List<Posts> postsList = postsRepository.findAll();

        // Then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("BaseTimeEntity_등록")
    public void testBaseTimeEntity() {

        // Given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.of("title", "content", "author"));

        // When
        List<Posts> postsList = postsRepository.findAll();

        // Then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate="
                + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
