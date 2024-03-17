package com.example.apisession.model;

import androidx.annotation.NonNull;

public class Posts {
    private int userId;
    private int id;
    private String title;
    private String body;

    // Getters
    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    // Setters with validation
    public void setUserId(int userId) {
        if (userId > 0) {
            this.userId = userId;
        } else {
            throw new IllegalArgumentException("User ID must be positive.");
        }
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID must be positive.");
        }
    }

    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
    }

    public void setBody(String body) {
        if (body != null && !body.trim().isEmpty()) {
            this.body = body;
        } else {
            throw new IllegalArgumentException("Body cannot be null or empty.");
        }
    }

    // toString method
    @NonNull
    @Override
    public String toString() {
        return "Posts{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
