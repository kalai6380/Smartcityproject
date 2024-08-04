package com.example.SmartCity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SmartCity.model.Chinese;
import com.example.SmartCity.model.Touristplace;

@Repository

public interface Chineserepository  extends JpaRepository <Chinese,Long>
{
	 List<Chinese> findByLocationContaining(String location);
}
