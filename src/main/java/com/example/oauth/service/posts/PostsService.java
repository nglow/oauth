package com.example.oauth.service.posts;

import com.example.oauth.domain.posts.Posts;
import com.example.oauth.domain.posts.PostsRepository;
import com.example.oauth.web.dto.PostsListResponseDto;
import com.example.oauth.web.dto.PostsResponseDto;
import com.example.oauth.web.dto.PostsSaveRequestDto;
import com.example.oauth.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto dto) {
        var posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Can't find posts by id transmitted"));
        posts.update(dto.getTitle(), dto.getContent());

        return posts.getId();
    }

    public PostsResponseDto findById(Long id) {
        var posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Can't find posts by id transmitted"));
        return PostsResponseDto.from(posts);
    }

    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::from)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        postsRepository.deleteById(id);
    }


}
