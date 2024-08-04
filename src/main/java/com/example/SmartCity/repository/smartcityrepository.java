package com.example.SmartCity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SmartCity.model.Smartcitymodel;

public interface smartcityrepository extends JpaRepository<Smartcitymodel, Long> {
    List<Smartcitymodel> findByUsernameContaining(String username);
    Smartcitymodel findByUsername(String username);
}
