package ru.bykov.urlshorter.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "url")
public class UrlModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "full_url")
    private String fullUrl;
    @Column(name = "short_url")
    private String shortUrl;
    @Column(name = "time")
    private Timestamp time;

    public UrlModel() {
    }

    public UrlModel(int id, String fullUrl, String shortUrl, Timestamp time) {
        this.id = id;
        this.fullUrl = fullUrl;
        this.shortUrl = shortUrl;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fulllink) {
        this.fullUrl = fulllink;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortlink) {
        this.shortUrl = shortlink;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
