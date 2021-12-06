package it1.doan.webapp.user.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService {

    public void upload(MultipartFile file) throws IOException {
        file.transferTo(new File("src/main/resources/static/img"+file.getOriginalFilename()));
    }
}
