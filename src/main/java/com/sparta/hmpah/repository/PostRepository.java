package com.sparta.hmpah.repository;

import com.sparta.hmpah.entity.Post;
import com.sparta.hmpah.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(User user);

    @Query("select p from Post p where p.user.username = :username")
    List<Post> findByUsername(@Param("username") String username);

    @Query("SELECT p FROM Post p WHERE p.title LIKE CONCAT('%', :keyword, '%')")
    List<Post> findByContainTitleKeyWord(@Param("keyword") String keyWord);

    @Query(value = "select p from Post p"
            , countQuery = "select count(p) from Post p")
        // 데이터 양이 많이지면 카운트 커리에 join이 필요 없어서 따로 분리 가능
    Page<Post> findPageAll(Pageable pageable);

}
