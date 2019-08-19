package com.mschoeffel.mydms.service;

import com.mschoeffel.mydms.model.Document;
import com.mschoeffel.mydms.model.Sender;
import com.mschoeffel.mydms.model.Tag;
import com.mschoeffel.mydms.model.Type;

import java.util.List;

public interface DocumentService {

    public List<Document> findAll();

    public Document findById(int id);

    public Document save(Document sender);

    public boolean existsId(int id);

    public void deleteById(int id);

    public List<Document> findBySender(Sender sender);
    public List<Document> findByType(Type type);
    public List<Document> findByTag(Tag tag);
    public List<Document> findAllByTitleContaining(String title);
    public List<Document> findAllByTextContaining(String text);

}
