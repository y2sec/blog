package com.y2sec.blog.domain;

import javassist.Loader;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private int views;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private String postDate;

    public void setCategory(Category category) {
        this.category = category;
        category.getPosts().add(this);
    }

    public static Post createPost(String title, String content, Category category) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);
        post.setPostDate(format.format(new Date()));
        post.setViews(0);

        return post;
    }

    public void delete() {

    }

    public Long updatePost(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;

        return id;
    }
}
