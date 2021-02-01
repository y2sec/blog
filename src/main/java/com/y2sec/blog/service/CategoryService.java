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

    public Long saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id);
    }

    public List<Category> findCategory() {
        return categoryRepository.findAll();
    }
}
