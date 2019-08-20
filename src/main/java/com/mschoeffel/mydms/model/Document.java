package com.mschoeffel.mydms.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "documents")
@DynamicUpdate()
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private int id;

    @Column(name = "path", updatable = false)
    private String path;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type", updatable = false)
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender", updatable = false)
    private Sender sender;

    @Column(name = "title")
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date", updatable = false)
    private LocalDate date;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", updatable = false)
    private User user;

    @Column(name = "number", updatable = false)
    @Formula(value = " concat(type, '-', id) ")
    private String number;

    @Column(name = "file", updatable = false)
    private String file;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "documents_tag",
            joinColumns = { @JoinColumn(name = "document") },
            inverseJoinColumns = { @JoinColumn(name = "tag") }
    )
    private List<Tag> tags;

    public Document() {
    }


    public void addTag(Tag tag){
        if (!tags.contains(tag)){
            tags.add(tag);
        }
    }

    public void removeTag(Tag tag){
        tags.remove(tag);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
