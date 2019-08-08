package com.mschoeffel.mydms.repository;

import com.mschoeffel.mydms.model.Document;
import com.mschoeffel.mydms.model.Sender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
}
