package com.y2sec.blog.controller;

import com.y2sec.blog.domain.Category;
import com.y2sec.blog.domain.Post;
import com.y2sec.blog.service.CategoryService;
import com.y2sec.blog.service.CommentService;
import com.y2sec.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final CategoryService categoryService;
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/post/new")
    public String createForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("categoryList", categoryService.findCategory());
        return "post/createPostForm";
    }

    @PostMapping("/post/new")
    public String create(@Valid PostForm postForm) {
        Category category = categoryService.findById(postForm.getCategoryId());
        Post post = Post.createPost(postForm.getTitle(), postForm.getContent(), category);
        Long postId = postService.savePost(post);

        return "redirect:/post/" + postId;
    }

    @GetMapping("/post/{postId}")
    public String postForm(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findById(postId);
        model.addAttribute("categoryList", categoryService.findCategory());
        model.addAttribute("post", post);
        CommentForm commentForm = new CommentForm();
        commentForm.setPostId(postId);
        model.addAttribute("commentForm", commentForm);

        return "post/postForm";
    }

    @GetMapping("/post/{postId}/delete")
    public String delete(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return "redirect:/";
    }

    @GetMapping("/post/{postId}/edit")
    public String updateForm(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findById(postId);
        PostForm postForm = new PostForm();
        postForm.setId(post.getId());
        postForm.setTitle(post.getTitle());
        postForm.setContent(post.getContent());
        postForm.setCategoryId(post.getCategory().getId());

        model.addAttribute("postForm", postForm);
        model.addAttribute("categoryList", categoryService.findCategory());
        model.addAttribute("selectCategory", categoryService.findById(postId));

        return "post/updatePostForm";
    }

    @PostMapping("/post/{postId}/edit")
    public String update(@ModelAttribute("postForm") PostForm postForm, @PathVariable("postId") Long postId, Model model) {
        Post post = postService.findById(postId);
//        Long id = post.updatePost(postForm.getTitle(), postForm.getContent(), categoryService.findById(postForm.getCategoryId()));
//        postService.savePost(post);
        Long id = postService.updatePost(postForm);
        return "redirect:/post/" + id;
    }
}
