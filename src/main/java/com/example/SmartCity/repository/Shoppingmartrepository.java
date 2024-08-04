package com.example.SmartCity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SmartCity.model.Shoppingmart;

@Repository
public interface Shoppingmartrepository extends JpaRepository <Shoppingmart,Long>
{
	 List<Shoppingmart> findByLocationContaining(String location);
}
