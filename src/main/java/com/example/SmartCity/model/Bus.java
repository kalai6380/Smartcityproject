package com.example.SmartCity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="bus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bus
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String Busno;

	private String Startingplace;
	private String location; 
	private String endingplace;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBusno() {
		return Busno;
	}
	public void setBusno(String busno) {
		Busno = busno;
	}
	
	public String getStartingplace() {
		return Startingplace;
	}
	public void setStartingplace(String startingplace) {
		Startingplace = startingplace;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getEndingplace() {
		return endingplace;
	}
	public void setEndingplace(String endingplace) {
		this.endingplace = endingplace;
	}
	@Override
	public String toString() {
		return "Bus [id=" + id + ", Busno=" + Busno + ", Startingplace=" + Startingplace + ", location=" + location
				+ ", endingplace=" + endingplace + "]";
	}
	
	
	
	

}
