package com.y2sec.blog.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentForm {

    private Long id;
    private Long postId;
    private String name;
    private String passwd;
    private String content;

}
