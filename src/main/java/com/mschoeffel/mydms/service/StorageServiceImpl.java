package com.mschoeffel.mydms.service;

import com.mschoeffel.mydms.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    @Autowired
    public StorageServiceImpl() {
        this.rootLocation = Paths.get("src/main/resources/storage");
    }

    public void store(MultipartFile file, Document document){
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new RuntimeException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
    }

    public String getCurrentPath(){
        return this.rootLocation.toString();
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public String buildStorageFilePath(Document document){
        return "\\" + document.getFile();
    }

    @Override
    public Resource loadAsResource(Document document) {
        try {
            Path file = Paths.get(rootLocation.toString() + buildStorageFilePath(document));
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists()) {
                return resource;
            }
            else {
                throw new RuntimeException(
                        "Could not read file: " + document.getFile());

            }
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + document.getFile(), e);
        }
    }
}
