package com.y2sec.blog.controller;

import com.y2sec.blog.domain.Post;
import com.y2sec.blog.service.CategoryService;
import com.y2sec.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final PostService postService;

    @GetMapping("/")
    public String home(@RequestParam(name = "page", required = false, defaultValue = "1") long page, Model model) {

        List<Post> posts = postService.findPosts();

        model.addAttribute("categoryList", categoryService.findCategory());
        model.addAttribute("pageNumber", page);
        model.addAttribute("postSize", posts.size());
        model.addAttribute("postList", posts.subList((int)Math.min((posts.size() / 10) * 10, (page-1) * 10), (int)Math.min(posts.size(), page * 10)));

        return "home";
    }

}
