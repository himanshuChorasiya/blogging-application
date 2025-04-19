package com.hblog.api.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor

public class UserDto {

    private int id;
	
    @NotEmpty
    @Size(min = 4,message="Username size must be greater or equal to 4")
	private String name;
    
    @Email
	private String email;
    
    @NotEmpty
    @Size(min = 4,max=10,message="Password size must be between 4 to 10")
	private String password;
    
    @NotEmpty
    @Size(min = 4,message="About size must be greater or equal to 4")
	private String about;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	
}
