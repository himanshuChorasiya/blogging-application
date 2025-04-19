package com.hblog.api.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hblog.api.entities.User;
import com.hblog.api.exceptions.ResourceNotFoundException;
import com.hblog.api.payloads.UserDto;
import com.hblog.api.respositories.UserRepo;
import com.hblog.api.services.UserService;

@Service
public class userServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);	
		return this.userToUserDto(savedUser);
	}

	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow((()->new ResourceNotFoundException("User","Id", userId)));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		return this.userToUserDto(updatedUser);
	}

	public UserDto getUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow((()->new ResourceNotFoundException("User","Id", userId)));
		return this.userToUserDto(user);
	}

	public List<UserDto> getAllUser() {
	    List<User> users= this.userRepo.findAll();
	   
		List<UserDto> userDtoList = users.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
		return userDtoList;
	}

	public void deleteUser(Integer userId) {
	
		User user = this.userRepo.findById(userId).orElseThrow((()->new ResourceNotFoundException("User","Id", userId)));
		this.userRepo.delete(user);
		
	}
	
	public User dtoToUser(UserDto userDto) {
		
//		User user = new User();
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		User user = modelMapper.map(userDto, User.class);
		return user;
	}
	
	public UserDto userToUserDto(User user) {
		
//		UserDto userDto = new UserDto();
//		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
