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
    @OneToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostImg> imgs;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="posts_books",
            joinColumns={@JoinColumn(name="post_id")},
            inverseJoinColumns={@JoinColumn(name="book_id")}
    )
    private List<Book> books ;


    public Post(){}

    public Post(long id, String title, String body, User user, List<PostImg> imgs, List<Book> books) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;
        this.imgs = imgs;
        this.books = books;
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
