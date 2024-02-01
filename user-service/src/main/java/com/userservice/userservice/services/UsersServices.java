package com.userservice.userservice.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.userservice.entity.Users;
import com.userservice.userservice.repository.UsersRepository;
import com.userservice.userservice.response.HotelsResponse;
import com.userservice.userservice.response.UsersResponse;
import com.userservice.userservice.service.UserClient;

@Service
public class UsersServices {

	@Autowired
	UsersRepository usersRepo;

	@Autowired
	UserClient userClient;

	@Autowired
	private ModelMapper mapper;

	public UsersResponse findUserDetails(int Id) {
		Optional<Users> data = usersRepo.findById(Id);
		UsersResponse userResponse = mapper.map(data, UsersResponse.class);
		return userResponse;
	}

	public HotelsResponse getHotelDetails(int userId) {
		return userClient.getHotelDetails(userId);
	}

}
