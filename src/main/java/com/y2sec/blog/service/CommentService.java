package com.y2sec.blog.service;

import com.y2sec.blog.domain.Comment;
import com.y2sec.blog.domain.Post;
import com.y2sec.blog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> findByPost(Post post) {
        return commentRepository.findByPost(post);
    }
}
