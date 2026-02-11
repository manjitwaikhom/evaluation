package com.example.demo.service;


import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class BookDTO {
    private String id;
    private String title;
    private String author;
    private Integer pageCount;

    public BookDTO( String title, String author, Integer pageCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
