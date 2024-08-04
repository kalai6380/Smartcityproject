package com.example.SmartCity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SmartCity.model.Italian;
@Repository
public interface Italianrepository extends JpaRepository <Italian,Long>
{
	 List<Italian> findByLocationContaining(String location);
}
