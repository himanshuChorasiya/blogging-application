package com.hblog.api.services;

import java.util.List;

import com.hblog.api.payloads.UserDto;

public interface UserService {

		UserDto createUser(UserDto user);
		
		UserDto updateUser(UserDto user, Integer userId);
		
		UserDto getUserById(Integer userid);
		
		List<UserDto> getAllUser();
		
		void deleteUser(Integer userId);
		
}
