package com.example.SmartCity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SmartCity.model.Hoteladd;
import com.example.SmartCity.model.Touristplace;

public interface Hoteladdrepository extends JpaRepository<Hoteladd,Long>
{
	List<Hoteladd> findByLocationContaining(String location);
}
