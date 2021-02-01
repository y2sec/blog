package com.y2sec.blog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String name;

    private String content;

    private String password;

    private LocalDateTime commentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    public static Comment createComment(String name, String content, String password) {
        Comment comment = new Comment();
        comment.setName(name);
        comment.setContent(content);
        comment.setPassword(password);

        return comment;
    }
}
