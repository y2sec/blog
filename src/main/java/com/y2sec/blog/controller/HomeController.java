package com.y2sec.blog.controller;

import com.y2sec.blog.service.CategoryService;
import com.y2sec.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final PostService postService;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("categoryList", categoryService.findCategory());
        model.addAttribute("postList", postService.findPosts());

        return "home";
    }

}
