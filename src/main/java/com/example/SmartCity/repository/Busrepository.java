package com.example.SmartCity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SmartCity.model.Bus;
import com.example.SmartCity.model.Touristplace;


@Repository
public interface Busrepository extends JpaRepository <Bus,Long>
{
	 List<Bus> findByLocationContaining(String location);
}


