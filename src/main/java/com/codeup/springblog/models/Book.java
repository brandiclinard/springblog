package com.codeup.springblog.models;


import javax.persistence.*;

@Entity
@Table(name = "books")// must do this to create the name of the table unless you want it to populate as post in MYSQL
public class Book {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false) // how to say NOT NULL in MYSQL
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String summary;
    @Column
    private String author;
    @Column
    private String imgPath;
    @OneToOne
    private Season season;

    public Book() {
    }

    public Book(String title, String author, String summary, String imgPath) {
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.imgPath = imgPath;
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
}
