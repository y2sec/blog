package com.y2sec.blog.repository;

import com.y2sec.blog.domain.Comment;
import com.y2sec.blog.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public Long save(Comment comment) {
        em.persist(comment);
        return comment.getId();
    }

    public List<Comment> findByPost(Post post) {
        return em.createQuery("select c from Comment c where c.post =: post", Comment.class)
                .setParameter("post", post)
                .getResultList();
    }
}
