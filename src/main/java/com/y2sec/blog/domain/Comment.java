package com.y2sec.blog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Entity
@Getter @Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String name;

    private String content;

    private String password;

    private String commentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    public static Comment createComment(String name, String content, String password, Post post) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        Locale.setDefault(Locale.KOREA);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
        Comment comment = new Comment();
        comment.setName(name);
        comment.setContent(content);
        comment.setPassword(password);
        comment.setPost(post);
        comment.setCommentDate(format.format(new Date()));

        return comment;
    }
    public Long updateComment(String name, String content) {
        this.name = name;
        this.content = content;

        return post.getId();
    }
}
