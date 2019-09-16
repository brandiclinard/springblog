package com.codeup.springblog.models;


import javax.persistence.*;

@Entity
@Table(name="statuses")
public class Status {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String status;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "book_id")
    private Book book;

    public Status() {
    }

    public Status(long id, String status, User user, Book book) {
        this.id = id;
        this.status = status;
        this.user = user;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
