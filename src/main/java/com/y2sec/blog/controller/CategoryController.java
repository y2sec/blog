package com.y2sec.blog.controller;

import com.y2sec.blog.domain.Category;
import com.y2sec.blog.domain.Post;
import com.y2sec.blog.service.CategoryService;
import com.y2sec.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final PostService postService;

    @GetMapping("/category/new")
    public String createForm(Model model) {
        model.addAttribute("categoryForm", new CategoryForm());
        return "category/createCategoryForm";
    }

    @PostMapping("/category/new")
    public String create(@Valid CategoryForm categoryForm) {
        Category category = Category.createCategory(categoryForm.getName());
        categoryService.saveCategory(category);

        return "redirect:/";
    }

    @GetMapping("/category/{id}")
    public String categoryForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.findById(id);
        List<Post> posts = postService.findByCategory(category);
        model.addAttribute("posts", posts);

        return "category/CategoryForm";
    }
}
