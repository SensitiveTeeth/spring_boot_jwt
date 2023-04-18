package com.auth.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "public_content")
public class PublicContent {
    @Id
    private Long id;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
