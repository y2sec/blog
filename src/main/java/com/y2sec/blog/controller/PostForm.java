package com.y2sec.blog.controller;

import com.y2sec.blog.domain.Category;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostForm {

    private String title;
    private String content;
    private Long categoryId;
}
