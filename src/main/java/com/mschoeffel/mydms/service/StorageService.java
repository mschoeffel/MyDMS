package com.mschoeffel.mydms.service;

import com.mschoeffel.mydms.model.Document;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    public void store(MultipartFile file, Document document);
    public String getCurrentPath();
    public Path load(String filename);
    public Resource loadAsResource(Document document);

    public String buildStoragePath(Document document);
    public String buildFileName(Document document);
}
