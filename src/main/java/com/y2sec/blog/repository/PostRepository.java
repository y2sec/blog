package com.y2sec.blog.repository;

import com.y2sec.blog.domain.Category;
import com.y2sec.blog.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public Long save(Post post) {
        if (post.getId() == null)
            em.persist(post);
        else {
            System.out.println("merge");
            em.merge(post);
            System.out.println("finish");
        }

        return post.getId();
    }

    public void delete(Long id) {
        em.remove(findById(id));
    }

    public Post findById(Long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findByTitle(String title) {
        return em.createQuery("select p from Post p where p.title =:title", Post.class)
                .setParameter("title", title)
                .getResultList();
    }

    public List<Post> findByCategory(Category category) {
        return em.createQuery("select p from Post p where p.category =:category", Post.class)
                .setParameter("category", category)
                .getResultList();
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class).getResultList();
    }
}
