package it1.doan.webapp.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class ControllerUpload {
    @PostMapping("/upload")
    public ResponseEntity<?> handlefileupload (@RequestParam("file") MultipartFile file){
        String filename = file.getOriginalFilename();
        try {
            file.transferTo(new File("D:\\1\\webshop-master\\webapp\\src\\main\\resources\\static\\admin\\img\\logo\\"  + filename));
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("tốt");
    }

    @PostMapping("/uploadkm")
    public ResponseEntity<?> handlefileuploadkm (@RequestParam("file") MultipartFile file){
        String filename = file.getOriginalFilename();
        try {
            file.transferTo(new File("D:\\1\\webshop-master\\webapp\\src\\main\\resources\\static\\admin\\img\\"  + filename));
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("tốt");
    }

    @PostMapping("/uploadsp")
    public ResponseEntity<?> handlefileuploadsp (@RequestParam("file") MultipartFile file){
        String filename = file.getOriginalFilename();
        try {
            file.transferTo(new File("D:\\1\\webshop-master\\webapp\\src\\main\\resources\\static\\admin\\img\\products\\"  + filename));
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("tốt");
    }
}
