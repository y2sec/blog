package com.y2sec.blog.controller;

import com.y2sec.blog.service.CategoryService;
import com.y2sec.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RootController {

    private final CategoryService categoryService;
    private final PostService postService;

    private String passwd = "rkdgus@3835";

    @GetMapping("/root")
    public String loginRoot(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "root/loginForm";
    }

    @PostMapping("/root/manage")
    public String manage(@Valid LoginForm loginForm) {
        if (loginForm.getPasswd().equals(passwd)) {
            return "root/manageForm";
        } else {
            return "redirect:/root";
        }
    }
}
