package com.mschoeffel.mydms.model;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="sender")
@DynamicUpdate
public class Sender {

    @Id
    @Column(name="id", updatable=false)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="text")
    private String text;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user", updatable = false)
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date", updatable = false)
    private LocalDate date;


    public Sender() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}