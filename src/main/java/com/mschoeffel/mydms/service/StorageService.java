package com.mschoeffel.mydms.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    public void store(MultipartFile file);
    public String getCurrentPath();
}
