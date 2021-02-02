package com.y2sec.blog.service;

import com.y2sec.blog.domain.Category;
import com.y2sec.blog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.delete(id);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id);
    }

    public List<Category> findCategory() {
        return categoryRepository.findAll();
    }
}
