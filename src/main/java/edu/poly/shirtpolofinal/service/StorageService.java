package edu.poly.shirtpolofinal.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageService {
    String getStoredFilename(MultipartFile file, String id);

    void store(MultipartFile file, String storedFilename);

    org.springframework.core.io.Resource loadAsResource(String filename);

    Path load(String filename);

    void delete(String storedFilename) throws IOException;

    void init();
}
