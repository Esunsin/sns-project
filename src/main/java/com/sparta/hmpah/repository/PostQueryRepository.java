package com.sparta.hmpah.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.hmpah.entity.Post;
import com.sparta.hmpah.entity.QPost;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sparta.hmpah.entity.QPost.post;

@Repository
@RequiredArgsConstructor
public class PostQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Post> findAll(){
        return queryFactory
                .select(post)
                .from(post)
                .fetch();
    }
    public Post findById(Long id) {
         return queryFactory
                 .select(post)
                 .from(post)
                 .where(post.id.eq(id))
                 .fetchOne();
    }
    public QueryResults<Post> findPageAll(){
        return queryFactory
                .select(post)
                .from(post)
                .orderBy(post.id.desc())
                .offset(0)
                .limit(5)
                .fetchResults();
    }
    //검색기능
    public List<Post> findByUsername(String username){
        return queryFactory
                .selectFrom(post)
                .where(post.user.username.eq(username))
                .fetch();
    }

    //포함된 단어 검색 기능
    public List<Post> findByContainedKeyWord(String keyWord) {
        return queryFactory
                .selectFrom(post)
                .where(post.title.contains(keyWord))
                .fetch();
    }
}
