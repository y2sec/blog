package com.y2sec.blog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Getter @Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    @Column(columnDefinition="TEXT")
    private String content;

    private int views;

    @ManyToOne(fetch = FetchType.EAGER)
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
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        Locale.setDefault(Locale.KOREA);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);
        post.setPostDate(format.format(new Date()));
        post.setViews(0);

        return post;
    }

    public void viewsUp() {
        this.views++;
    }

    public Long updatePost(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;

        return id;
    }
}
