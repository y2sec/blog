package com.y2sec.blog.service;

import com.y2sec.blog.domain.Category;
import com.y2sec.blog.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void 카테고리_추가() {
        Category category = Category.createCategory("카테고리");
        Long id = categoryService.saveCategory(category);

        Category findCategory = categoryService.findById(id);

        Assertions.assertThat(category).isEqualTo(findCategory);

    }

    @Test
    void 카테고리_삭제() {
        Category category = Category.createCategory("카테고리");
        Long id = categoryService.saveCategory(category);
        Category findCategory = categoryService.findById(id);

        Assertions.assertThat(category).isEqualTo(findCategory);
        categoryService.deleteCategory(id);

        Category deleteCategory = categoryService.findById(id);
        Assertions.assertThat(deleteCategory).isNull();
    }

    @Test
    void 카테고리_조회() {
        Category category1 = Category.createCategory("카테고리1");
        categoryService.saveCategory(category1);
        Category category2 = Category.createCategory("카테고리1");
        categoryService.saveCategory(category2);

        List<Category> categories = categoryService.findCategory();

        Assertions.assertThat(categories.size()).isEqualTo(2);
    }

}