package com.codeup.springblog.models;

import javax.persistence.*;

@Entity(name = "posts")// must do this to create the name of the table unless you want it to populate as post in MYSQL
public class Post {
    @Id @GeneratedValue
    private long id;
    @Column(nullable = false) // how to say NOT NULL in MYSQL
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    public Post(){}

    public Post(long id, String title, String body) {
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
