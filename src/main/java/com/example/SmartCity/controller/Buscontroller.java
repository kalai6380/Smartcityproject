package com.example.SmartCity.controller;

import java.util.List;
import java.util.Optional;

import com.example.SmartCity.model.Bus;
import com.example.SmartCity.model.Touristplace;
import com.example.SmartCity.repository.Busrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class Buscontroller {

    @Autowired
    private Busrepository eRepo;

    
    @GetMapping("/bus")
    public List<Bus> getAllbus() {
        return eRepo.findAll();
    }

    @GetMapping("/bus/{id}")
    public ResponseEntity<Bus> getHotelById(@PathVariable Long id) {
        Optional<Bus> hotel = eRepo.findById(id);
        if (hotel.isPresent()) {
            return new ResponseEntity<>(hotel.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/bus")
    public Bus saveHotel(@RequestBody Bus bus) {
        return eRepo.save(bus);
    }
    
    @GetMapping("/bus/search")
    public List<Bus> getplace(@RequestParam String location) {
        return eRepo.findByLocationContaining(location);
    }
    @PutMapping("/bus/{id}")
    public Bus updatebus(@PathVariable Long id, @RequestBody Bus updatedbus) throws Exception {
        if (!eRepo.existsById(id)) {
            // Handle the case when hotel is not found, you can throw an exception or return null
            // For simplicity, let's throw an exception
            throw new Exception("Hotel not found with id: " + id);
        }
        updatedbus.setId(id); // Ensure the ID is set to the updated hotel object
        return eRepo.save(updatedbus);
    }



    @DeleteMapping("/bus/{id}")
    public ResponseEntity<HttpStatus> deletebus (@PathVariable Long id)
    {
         eRepo.deleteById(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
