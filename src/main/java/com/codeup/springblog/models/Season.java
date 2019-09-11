package com.codeup.springblog.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="seasons")
public class Season {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String season;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "season")
    private List<Book> books;


    public Season() {
    }

    public Season(long id, String season, List<Book> books) {
        this.id = id;
        this.season = season;
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
