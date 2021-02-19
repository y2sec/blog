package com.y2sec.blog.repository;

import com.y2sec.blog.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    public Long save(Category category) {
        if (category.getId() == null)
            em.persist(category);
        else
            em.merge(category);

        return category.getId();
    }

    public void delete(Long id) {
        em.remove(findById(id));
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class).getResultList();
    }

}
