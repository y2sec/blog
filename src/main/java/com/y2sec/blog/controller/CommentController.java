package com.y2sec.blog.controller;

import com.y2sec.blog.domain.Comment;
import com.y2sec.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Comment comment = commentService.findById(id);
        CommentForm commentForm = new CommentForm();

        commentForm.setName(comment.getName());
        commentForm.setContent(comment.getContent());

        model.addAttribute("id", id);
        model.addAttribute("commentForm", commentForm);

        return "comment/updateCommentForm";
    }

    @PostMapping("/comment/{id}/edit")
    public String update(@PathVariable("id") Long id, @Valid CommentForm commentForm) {
        Comment comment = commentService.findById(id);
        Long postId = comment.updateComment(commentForm.getName(), commentForm.getContent());
        commentService.saveComment(comment);

        return "comment/updateClose";
    }

    @GetMapping("/comment/{id}/delete")
    public String deleteComment(@PathVariable("id") Long id) {
        Long postId = commentService.deleteComment(id);

        return "comment/deleteClose";
    }
}
