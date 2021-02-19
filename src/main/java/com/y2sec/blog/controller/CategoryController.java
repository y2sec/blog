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

    @GetMapping("/category/{id}/{pageNum}")
    public String categoryForm(@PathVariable("id") Long id, @PathVariable("pageNum") Long pageNum, Model model) {
        Category category = categoryService.findById(id);
        List<Post> posts = postService.findByCategory(category);
        model.addAttribute("categoryList", categoryService.findCategory());
        model.addAttribute("pageNumber", 1);
        model.addAttribute("postSize", posts.size());
        model.addAttribute("category", category);
        model.addAttribute("postList", posts.subList((int)Math.min((posts.size() / 10) * 10, (pageNum-1) * 10), (int)Math.min(posts.size(), pageNum * 10)));



        return "category/categoryForm";
    }

    @GetMapping("/category/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/";
    }

    @GetMapping("/category/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.findById(id);
        CategoryForm categoryForm = new CategoryForm();

        categoryForm.setId(category.getId());
        categoryForm.setName(category.getName());
        model.addAttribute("categoryForm", categoryForm);

        return "category/updateCategoryForm";
    }

    @PostMapping("/category/{id}/edit")
    public String update(@PathVariable("id") Long id, @Valid CategoryForm categoryForm) {
        Category category = categoryService.findById(id);

        category.updateCategory(categoryForm.getName());
        categoryService.saveCategory(category);

        return "redirect:/";
    }

}
