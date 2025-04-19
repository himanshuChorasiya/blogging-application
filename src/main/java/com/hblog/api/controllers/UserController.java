package com.hblog.api.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hblog.api.payloads.ApiResponse;
import com.hblog.api.payloads.UserDto;
import com.hblog.api.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createdUserDto,HttpStatus.CREATED);
	}
	
	//update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable ("userId") Integer userId){
		UserDto updatedUserDto = userService.updateUser(userDto,userId);
		return new ResponseEntity<UserDto>(updatedUserDto,HttpStatus.CREATED);
		}
	
	//Delete user
		@DeleteMapping("/{userId}")
		public ResponseEntity<ApiResponse> deleteUser(@PathVariable ("userId") Integer userId){
		    userService.deleteUser(userId);
			return ResponseEntity.ok(new ApiResponse("User Deleted Successfully", true));
			}
//	@DeleteMapping("/{userId}")
//	public ResponseEntity<?> deleteUser(@PathVariable ("userId") Integer userId){
//	    userService.deleteUser(userId);
//		return ResponseEntity.ok(Map.of("User Deleted Successfully", "success"));
//		}
//	
	//GetUser
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> GetUser(@PathVariable ("userId") Integer userId){
		UserDto userDto = userService. getUserById(userId);
		return ResponseEntity.ok(userDto);
		}
	
	//GetAllUser
		@GetMapping("/")
		public ResponseEntity<List<UserDto>> GetAllUser(){
			List<UserDto> list = userService. getAllUser();
			return ResponseEntity.ok(list);
			}
	
}
