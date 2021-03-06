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
    public List<Posts> retrievePostsList() {
        return queryFactory.selectFrom(posts)
                .offset(0 * 4)
                .limit(4)
                .orderBy(posts.id.desc()).fetch();
    }
}
