package com.codeup.springblog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")// must do this to create the name of the table unless you want it to populate as post in MYSQL
public class Post {
    @Id @GeneratedValue
    private long id;
    @Column(nullable = false) // how to say NOT NULL in MYSQL
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostImg> imgs;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn (name = "book_id")
    private Book book;


    public Post(){}

    public Post(long id,String title, String body, List<PostImg> imgs, User user, Book book) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.imgs = imgs;
        this.user = user;
        this.book = book;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PostImg> getImgs() {
        return imgs;
    }

    public void setImgs(List<PostImg> imgs) {
        this.imgs = imgs;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
