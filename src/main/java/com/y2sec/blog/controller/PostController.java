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

@Controller
@RequiredArgsConstructor
public class PostController {

    private final CategoryService categoryService;
    private final PostService postService;

    @GetMapping("/post/new")
    public String createForm(Model model) {
        model.addAttribute("postForm", new PostForm());

        return "post/createPostForm";
    }

    @PostMapping("/post/new")
    public String create(@Valid PostForm postForm) {
        Category category = categoryService.findById(postForm.getCategoryId());
        Post post = Post.createPost(postForm.getTitle(), postForm.getContent(), category);
        postService.savePost(post);

        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String postForm(@PathVariable("id") Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);

        return "post/postForm";
    }
}
