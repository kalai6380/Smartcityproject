package com.example.SmartCity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SmartCity.model.Contact;


@Repository
public interface Contactrepository extends JpaRepository <Contact,Long>
{

}
