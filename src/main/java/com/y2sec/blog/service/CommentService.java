package com.y2sec.blog.service;

import com.y2sec.blog.controller.CommentForm;
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
    public Long saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    public Long deleteComment(Long id) {
        return commentRepository.delete(id);
    }

    @Transactional
    public Long updateComment(CommentForm commentForm) {
        Comment comment = commentRepository.findById(commentForm.getId());

        comment.setName(commentForm.getName());
        comment.setContent(commentForm.getContent());

        return comment.getId();
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> findByPost(Post post) {
        return commentRepository.findByPost(post);
    }
}
