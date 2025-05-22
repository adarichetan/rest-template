package com.dss.rest_template_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Task {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task() {
    }

    public Task(Long id, String status, String name, User user) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String name;
    private String status;

    private User user;
}
