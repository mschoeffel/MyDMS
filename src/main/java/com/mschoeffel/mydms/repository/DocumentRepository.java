package com.mschoeffel.mydms.repository;

import com.mschoeffel.mydms.model.Document;
import com.mschoeffel.mydms.model.Sender;
import com.mschoeffel.mydms.model.Tag;
import com.mschoeffel.mydms.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

    public List<Document> findBySender(Sender sender);
    public List<Document> findByType(Type type);
    public List<Document> findByTags(Tag tag);
    public List<Document> findAllByTitleContaining(String title);
    public List<Document> findAllByTextContaining(String text);
}
