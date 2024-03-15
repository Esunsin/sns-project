package com.sparta.hmpah.post;

import com.querydsl.core.QueryResults;
import com.sparta.hmpah.entity.Post;
import com.sparta.hmpah.repository.PostQueryRepository;
import com.sparta.hmpah.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class PostServiceSpringbootTest {

    @Autowired
    PostQueryRepository postQueryRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("querydsl을 이용하여 포스트 가지고 오기")
    void getPosts(){
        //given
        List<Post> queryRepositoryPosts = postQueryRepository.findAll();
        List<Post> repositoryPosts = postRepository.findAll();

        assertThat(queryRepositoryPosts.get(0)).isEqualTo(repositoryPosts.get(0));
        assertThat(queryRepositoryPosts.size()).isEqualTo(repositoryPosts.size());
    }
    @Test
    @DisplayName("paging 하기")
    void page(){
        QueryResults<Post> pageAll = postQueryRepository.findPageAll();
        List<Post> results = pageAll.getResults();
        for (Post result : results) {
            System.out.println("result.getId() = " + result.getId());
        }
    }

    @Test
    @DisplayName("유저 이름으로 찾기")
    void findByUsername(){
        //given
        List<Post> postRepositoryByUsername = postRepository.findByUsername("jinchan0");
        List<Post> postQueryRepositoryByUsername = postQueryRepository.findByUsername("jinchan0");
        //when
        for (Post post : postRepositoryByUsername) {
            System.out.println("post.getId() = " + post.getId());
        }
        System.out.println(" =============== ");
        for (Post post : postQueryRepositoryByUsername) {
            System.out.println("post.getId() = " + post.getId());
        }
        //then
        assertThat(postRepositoryByUsername.size()).isEqualTo(postQueryRepositoryByUsername.size());
        assertThat(postRepositoryByUsername.get(1)).isEqualTo(postQueryRepositoryByUsername.get(1));
    }
    @Test
    @DisplayName("키워드로 검색")
    void containKeyWord(){
        List<Post> byContainedKeyWord = postQueryRepository.findByContainedKeyWord("tit");
        for (Post post : byContainedKeyWord) {
            System.out.println(post.getTitle());
        }
        List<Post> byContainTitleKeyWord = postRepository.findByContainTitleKeyWord("tit");
        for (Post post : byContainTitleKeyWord) {
            System.out.println(post.getTitle());
        }

    }
}
