package com.example.SmartCity.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.SmartCity.model.Hoteladd;

import com.example.SmartCity.repository.Hoteladdrepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Hoteladdcontroller {

    private static final Logger logger = LoggerFactory.getLogger(Hoteladdcontroller.class);
    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private Hoteladdrepository hotelRepository;
    
    private String saveImage(MultipartFile imageFile) throws IOException {
    	
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
        logger.info("Image saved at: " + path.toString());
        return imageFile.getOriginalFilename();
    }
    @PostMapping("/hoteladd")
    public Hoteladd createHotel(@RequestParam("name") String name,@RequestParam("location") String location, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        String imagePath = saveImage(imageFile);
        Hoteladd hotel = new Hoteladd();
        hotel.setName(name);
        hotel.setLocation(location);
        hotel.setImagePath(imagePath);
        return hotelRepository.save(hotel);
    }
    @PutMapping("/hoteladd/{id}")
    public Hoteladd updateHotel(@PathVariable Long id, @RequestParam("name") String name, @RequestParam("location") String location,@RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        Hoteladd existingHotel = hotelRepository.findById(id).orElse(null);
        if (existingHotel != null) {
            existingHotel.setName(name);
            existingHotel.setLocation(location);
            if (!imageFile.isEmpty()) {
                String imagePath = saveImage(imageFile);
                existingHotel.setImagePath(imagePath);
            }
            return hotelRepository.save(existingHotel);
        }
        return null;
    }
    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
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

   
    @GetMapping("/hoteladd")
    public List<Hoteladd> getAllHotels() {
        return hotelRepository.findAll();
    }

    @GetMapping("/hoteladd/{id}")
    public Hoteladd getHotelById(@PathVariable Long id) 
    {
        return hotelRepository.findById(id).orElse(null);
    }
    @GetMapping("/hoteladd/search")
    public List<Hoteladd> getTouristplacesByLocation(@RequestParam String location) {
        return hotelRepository.findByLocationContaining(location);
    }
   
 
  
    @DeleteMapping("/hoteladd/{id}")
    public void deleteHotel(@PathVariable Long id) {
        hotelRepository.deleteById(id);
    }

   
}
