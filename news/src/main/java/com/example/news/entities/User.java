package com.example.news.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {
    private Integer id;
    @NotNull(message = "username can't be null")
    @NotEmpty(message = "username cant be empty")
    private String username;
    @NotNull(message = "role can't be null")
    @NotEmpty(message = "role cant be empty")
    private String role;
    private String hashedPassword;
    @NotNull(message = "email can't be null")
    @NotEmpty(message = "email cant be empty")
    private String email;
    @NotNull(message = "status can't be null")
    @NotEmpty(message = "status cant be empty")
    private String status;

    public User() {
    }

    public User(Integer id, String username, String role, String hashedPassword, String email, String status) {
        this();
        this.id = id;
        this.username = username;
        this.role = role;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
