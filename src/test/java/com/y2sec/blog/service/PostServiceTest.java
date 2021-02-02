package com.y2sec.blog.service;

import com.y2sec.blog.domain.Category;
import com.y2sec.blog.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    CategoryService categoryService;
    @Autowired
    PostService postService;

    @Test
    void 게시글_작성() {
        Category category = Category.createCategory("스프링");
        categoryService.saveCategory(category);
        Post post = Post.createPost("스프링 제목", "스프링 내용", category);
        Long postId = postService.savePost(post);

        Post findPost = postService.findById(postId);

        Assertions.assertThat(post).isEqualTo(findPost);
    }

    @Test
    void 게시글_삭제() {
        Category category = Category.createCategory("스프링");
        categoryService.saveCategory(category);
        Post post = Post.createPost("스프링 제목", "스프링 내용", category);
        Long postId = postService.savePost(post);

        Post findPost = postService.findById(postId);

        Assertions.assertThat(post).isEqualTo(findPost);
        postService.deletePost(postId);

        Assertions.assertThat(postService.findById(postId)).isNull();
    }

    @Test
    void 카테고리_삭제후_게시물() {
        Category category = Category.createCategory("스프링");
        Long categoryId = categoryService.saveCategory(category);
        Post post = Post.createPost("스프링 제목", "스프링 내용", category);
        Long postId = postService.savePost(post);

        Post findPost = postService.findById(postId);
        Assertions.assertThat(post).isEqualTo(findPost);
        categoryService.deleteCategory(categoryId);

        Assertions.assertThat(postService.findById(postId)).isNull();
    }

    @Test
    void 게시물_조회() {
        Category category = Category.createCategory("스프링");
        categoryService.saveCategory(category);
        Post post = Post.createPost("스프링 제목", "스프링 내용", category);
        Long postId = postService.savePost(post);
        Post post1 = Post.createPost("스프링 제목2", "스프링 내용2", category);
        postService.savePost(post1);

        List<Post> posts = postService.findPosts();

        Assertions.assertThat(posts.size()).isEqualTo(2);
    }
}