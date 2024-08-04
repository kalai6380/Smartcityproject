package com.example.SmartCity.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.SmartCity.model.Practice;
import com.example.SmartCity.repository.Privaterepository;

import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PracticeController {
    private static final Logger logger = LoggerFactory.getLogger(PracticeController.class);
    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private Privaterepository repo;

    public String saveImage(MultipartFile imageFile) throws IOException {
        if (imageFile.isEmpty()) {
            return null;
        }
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        byte[] bytes = imageFile.getBytes();
        Path path = uploadPath.resolve(imageFile.getOriginalFilename());
        Files.write(path, bytes, StandardOpenOption.CREATE);
        logger.info(path.toString());
        return imageFile.getOriginalFilename();
    }

    @PostMapping("/kalai")
    public Practice create(@RequestParam("name") String name, 
                           @RequestParam("location") String location, 
                           @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        String imagePath = saveImage(imageFile);
        Practice kalai = new Practice();
        kalai.setName(name);
        kalai.setLocation(location);
        kalai.setImagePath(imagePath);
        return repo.save(kalai);
    }

    @PutMapping("/kalai/{id}")
    public Practice update(@PathVariable Long id, 
                           @RequestParam("name") String name, 
                           @RequestParam("location") String location, 
                           @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        Practice pra = repo.findById(id).orElse(null);
        if (pra != null) {
            pra.setName(name);
            pra.setLocation(location); // Fix this line
            if (imageFile != null && !imageFile.isEmpty()) {
                String imagePath = saveImage(imageFile);
                pra.setImagePath(imagePath);
            }
        }
        return repo.save(pra);
    }

    @GetMapping("/kalai")
    public List<Practice> getAll() {
        return repo.findAll();
    }

    @GetMapping("/kalai/{id}")
    public Practice findById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/kalai/search")
    public List<Practice> findByLocation(@RequestParam String location) {
        return repo.findByLocationContaining(location);
    }

    @GetMapping("/kalai/kalai/{filename}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/kalai/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
