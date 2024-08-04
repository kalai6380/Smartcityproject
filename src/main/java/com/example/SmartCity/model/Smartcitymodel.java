package com.example.SmartCity.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="signup")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Smartcitymodel 
{
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private long id;
		private String name;
		private String username;
		private String phonenumber;
		 private LocalDate dateofbirth; 
		private String email;
		private String city;
		private String password;
		private String confirmpassword;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPhonenumber() {
			return phonenumber;
		}
		public void setPhonenumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}
		public LocalDate getDateofbirth() {
			return dateofbirth;
		}
		public void setDateofbirth(LocalDate dateofbirth) {
			this.dateofbirth = dateofbirth;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getConfirmpassword() {
			return confirmpassword;
		}
		public void setConfirmpassword(String confirmpassword) {
			this.confirmpassword = confirmpassword;
		}
		@Override
		public String toString() {
			return "Smartcitymodel [id=" + id + ", name=" + name + ", username=" + username + ", phonenumber="
					+ phonenumber + ", dateofbirth=" + dateofbirth + ", email=" + email + ", city=" + city
					+ ", password=" + password + ", confirmpassword=" + confirmpassword + "]";
		}
			
		
		
}
