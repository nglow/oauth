package com.example.oauth.domain.posts;

import java.util.List;

public interface PostsCustomRepository {

    List<Posts> retrievePostsList(long offset, long limit);
}
