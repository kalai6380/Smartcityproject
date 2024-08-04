package com.example.SmartCity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SmartCity.model.Smartcitymodel;
import com.example.SmartCity.repository.smartcityrepository;


@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class Smartcitycontroller {

    @Autowired
    private smartcityrepository city;
    
    @PostMapping("/signup")
    public String signUp(@RequestBody Smartcitymodel user) {
        if (city.findByUsername(user.getUsername()) != null) {
            return "Username already exists";
        }
        city.save(user);
        return "User successfully registered!";
    }

    @PostMapping("/login")
    public String login(@RequestBody Smartcitymodel user) {
        Smartcitymodel existingUser = city.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "Login successful!";
        } else {
            return "Invalid username or password!";
        }
    }
    
    @PutMapping("/signup/{id}")
    public Smartcitymodel updateHotel(@PathVariable Long id, @RequestBody Smartcitymodel updatedHotel) throws Exception {
        if (!city.existsById(id)) {
            throw new Exception("Hotel not found with id: " + id);
        }
        updatedHotel.setId(id);
        System.out.println("Updating hotel: " + updatedHotel);
        return city.save(updatedHotel);
    }

    @GetMapping("/signup/{username}")
    public ResponseEntity<Smartcitymodel> getUserByUsername(@PathVariable String username) {
        Smartcitymodel user = city.findByUsername(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }	
}
