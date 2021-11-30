package com.example.oauth.web;

import com.example.oauth.domain.posts.Posts;
import com.example.oauth.service.posts.PostsService;
import com.example.oauth.web.dto.PostsSaveRequestDto;
import com.example.oauth.web.dto.PostsUpdateRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostsApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PostsService postsService;

    @Test
    @DisplayName("Post 저장 테스트")
    @WithMockUser(roles="USER")
    public void testSave() throws Exception {
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.of(title, content, "author");

        when(postsService.save(requestDto)).thenReturn(1L);

        String uri = "/api/v1/posts";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Post 저장 권한 테스트")
    public void testSaveWithoutSession() throws Exception {
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.of(title, content, "author");

        when(postsService.save(requestDto)).thenReturn(1L);

        String uri = "/api/v1/posts";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Post 수정 테스트")
    @WithMockUser(roles = "USER")
    public void testUpdate() throws Exception {

        Long id = 1L;
        String title = "titleModified";
        String content = "contentModified";
        var requestDto = PostsUpdateRequestDto.of(title, content);

        when(postsService.update(id, requestDto)).thenReturn(id);

        String uri = "/api/v1/posts/" + id;
        mockMvc.perform(patch(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Post 수정 권한 테스트")
    public void testUpdateWithoutSession() throws Exception {

        Long id = 1L;
        String title = "titleModified";
        String content = "contentModified";
        var requestDto = PostsUpdateRequestDto.of(title, content);

        when(postsService.update(id, requestDto)).thenReturn(id);

        String uri = "/api/v1/posts/" + id;
        mockMvc.perform(patch(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().is3xxRedirection());
    }


}