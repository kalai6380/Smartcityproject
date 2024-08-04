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

import com.example.SmartCity.model.Italian;
import com.example.SmartCity.model.Shoppingmart;
import com.example.SmartCity.repository.Italianrepository;
import com.example.SmartCity.repository.Shoppingmartrepository;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class Shoppingmartcontroller 
{
	private static final Logger logger = LoggerFactory.getLogger(Italiancontroller.class);
    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private Shoppingmartrepository hotelRepository;

    @GetMapping("/mart")
    public List<Shoppingmart> getAllHotels() {
        return hotelRepository.findAll();
    }

    @GetMapping("/mart/id/{id}")
    public Shoppingmart gettouristplaceById(@PathVariable Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    @PostMapping("/mart")
    public Shoppingmart createHotel(@RequestParam("placename") String placename, @RequestParam("location") String location, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        String imagePath = saveImage(imageFile);
        Shoppingmart place = new Shoppingmart();
        place.setPlacename(placename);
        place.setLocation(location);
        place.setImagePath(imagePath);
        return hotelRepository.save(place);
    }
//    @GetMapping("/place/location/{location}")
//    public List<Touristplace> getTouristplacesByLocation(@RequestParam String location) {
//        return tourist.findByLocationContaining(location);
//    }
    @GetMapping("/mart/search")
    public List<Shoppingmart> getTouristplacesByLocation(@RequestParam String location) {
        return hotelRepository.findByLocationContaining(location);
    }
    @PutMapping("/mart/{id}")
    public Shoppingmart updateHotel(@PathVariable Long id, @RequestParam("placename") String name, @RequestParam("location") String location, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
    	Shoppingmart existingHotel = hotelRepository.findById(id).orElse(null);
        if (existingHotel != null) {
            existingHotel.setPlacename(name);
            existingHotel.setLocation(location);
            if (!imageFile.isEmpty()) {
                String imagePath = saveImage(imageFile);
                existingHotel.setImagePath(imagePath);
            }
            return hotelRepository.save(existingHotel);
        }
        return null;
    }
    
    @DeleteMapping("/mart/{id}")
    public void deleteHotel(@PathVariable Long id) {
    	hotelRepository.deleteById(id);
    }

    @GetMapping("/mart/mart/{filename}")
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
}
