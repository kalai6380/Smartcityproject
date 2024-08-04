package com.example.SmartCity.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SmartCity.model.Touristplace;

public interface Touristplacerepository extends JpaRepository<Touristplace,Long>
{
    List<Touristplace> findByLocationContaining(String location);
}
