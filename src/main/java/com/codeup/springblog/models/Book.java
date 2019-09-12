package com.codeup.springblog.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")// must do this to create the name of the table unless you want it to populate as post in MYSQL
public class Book {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false) // how to say NOT NULL in MYSQL
    private String title;
    @Column
    private String author;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String summary;
    @Column
    private String imgPath;
    @ManyToOne
    @JoinColumn(name="season_id")
    private Season season;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<Post> posts;
    @ManyToMany(mappedBy = "books")
    private List<User> users;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<Status> statuses;

    public Book() {
    }

    public Book(long id, String title, String author, String summary, String imgPath, Season season, List<Post> posts, List<User> users, List<Status> statuses) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.imgPath = imgPath;
        this.season = season;
        this.posts = posts;
        this.users = users;
        this.statuses = statuses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }
}
