package com.codeup.springblog.models;
import javax.persistence.*;

@Entity
@Table(name="seasons")
public class Season {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String season;

    public Season() {
    }

    public Season(long id, String season) {
        this.id = id;
        this.season = season;
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
}
