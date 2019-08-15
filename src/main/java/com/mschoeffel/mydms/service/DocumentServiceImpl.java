package com.mschoeffel.mydms.service;

import com.mschoeffel.mydms.model.Document;
import com.mschoeffel.mydms.model.Sender;
import com.mschoeffel.mydms.model.Tag;
import com.mschoeffel.mydms.model.Type;
import com.mschoeffel.mydms.repository.DocumentRepository;
import com.mschoeffel.mydms.repository.SenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {

    private DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }


    @Override
    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    @Override
    public Document findById(int id) {
        Optional<Document> document = documentRepository.findById(id);
        return document.orElse(null);
    }

    @Override
    public boolean existsId(int id) {
        return documentRepository.existsById(id);
    }

    @Override
    public Document save(Document sender) {
        return documentRepository.save(sender);
    }

    @Override
    public void deleteById(int id) {
        documentRepository.deleteById(id);
    }

    @Override
    public List<Document> findBySender(Sender sender){
        return documentRepository.findBySender(sender);
    }

    @Override
    public List<Document> findByType(Type type){
        return documentRepository.findByType(type);
    }

    @Override
    public List<Document> findByTag(Tag tag){
        return documentRepository.findByTags(tag);
    }
}
