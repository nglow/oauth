package com.example.oauth.domain.posts;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.oauth.domain.posts.QPosts.posts;


public class PostsCustomRepositoryImpl implements PostsCustomRepository{

    private final JPAQueryFactory queryFactory;

    public PostsCustomRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public List<Posts> retrievePostsList(long offset, long limit) {
        return queryFactory.selectFrom(posts)
                .offset(offset * limit)
                .limit(limit)
                .orderBy(posts.id.desc()).fetch();
    }
}
