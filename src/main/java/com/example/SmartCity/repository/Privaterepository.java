package com.example.SmartCity.repository;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SmartCity.model.Practice;

public interface Privaterepository extends JpaRepository<Practice, Long> {
    List<Practice> findByLocationContaining(String location);
    Practice findByLocation(String location);
}

