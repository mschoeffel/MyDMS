package com.mschoeffel.mydms.model;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="users")
@DynamicUpdate
public class User {

    @Id
    @Column(name="username", updatable=false)
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="enabled")
    private Boolean enabled;

    @Column(name="name")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date", updatable = false)
    private LocalDate date;

    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Type> types;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
