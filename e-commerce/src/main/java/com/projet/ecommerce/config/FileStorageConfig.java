package com.projet.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

@Configuration
public class FileStorageConfig {

    @Value("${app.upload.dir:${user.home}/Images}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        File dir = new File(uploadDir);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new RuntimeException("Could not create upload directory: " + uploadDir);
        }
    }

    public String getUploadDir() {
        return uploadDir;
    }
}
