package com.example.SmartCity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Touristplace 
{
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String placename;
	    
		private String imagePath; // Store the file path
		private String location;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getPlacename() {
			return placename;
		}
		public void setPlacename(String placename) {
			this.placename = placename;
		}
		public String getImagePath() {
			return imagePath;
		}
		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		@Override
		public String toString() {
			return "Touristplace [id=" + id + ", placename=" + placename + ", imagePath=" + imagePath + ", location="
					+ location + "]";
		}
		

	    

}
