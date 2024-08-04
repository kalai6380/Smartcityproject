package com.example.SmartCity.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SmartCity.model.Contact;
import com.example.SmartCity.repository.Contactrepository;


@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class Contactcontroller {
	 @Autowired 
	    private Contactrepository eRepo;

	    @GetMapping("/contact")
	    public List<Contact> getAllHotels() {
	        return eRepo.findAll();
	    }

	    @GetMapping("/contact/{id}")
	    public ResponseEntity<Contact> getHotelById(@PathVariable Long id) {
	        Optional<Contact> hotel = eRepo.findById(id);
	        if (hotel.isPresent()) {
	            return new ResponseEntity<>(hotel.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @PostMapping("/contact")
	    public Contact saveHotel(@RequestBody Contact hotels) {
	        System.out.println("Saving hotel: " + hotels); // Log the data being saved
	        return eRepo.save(hotels);
	    }

	    @PutMapping("/contact/{id}")
	    public Contact updateHotel(@PathVariable Long id, @RequestBody Contact updatedHotel) throws Exception {
	        if (!eRepo.existsById(id)) {
	            throw new Exception("Hotel not found with id: " + id);
	        }
	        updatedHotel.setId(id); // Ensure the ID is set to the updated hotel object
	        System.out.println("Updating hotel: " + updatedHotel); // Log the data being updated
	        return eRepo.save(updatedHotel);
	    }

	    @DeleteMapping("/contact/{id}")
	    public ResponseEntity<HttpStatus> deletehotels(@PathVariable Long id) {
	        eRepo.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
}
