package com.example.SmartCity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SmartCity.model.Indian;
import com.example.SmartCity.model.Touristplace;

@Repository

public interface Indianrepository extends JpaRepository <Indian,Long>
{
	 List<Indian> findByLocationContaining(String location);
	

}
