package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name="post_imgs")
public class PostImg {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String path;

    @ManyToOne
    @JoinColumn (name = "post_id")
    private Post post;

    public PostImg() {
    }

    public PostImg(String path, Post post) {
        this.path = path;
        this.post = post;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
