package com.y2sec.blog.service;

import com.y2sec.blog.domain.Category;
import com.y2sec.blog.domain.Post;
import com.y2sec.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void savePost(String title, String content, Category category) {
        Post post = Post.createPost(title, content, category);
        postRepository.save(post);
    }

    public Post findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> findByCategory(Category category) {
        return postRepository.findByCategory(category);
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }
}
