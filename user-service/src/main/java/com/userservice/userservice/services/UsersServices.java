package com.userservice.userservice.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.userservice.entity.Users;
import com.userservice.userservice.repository.UsersRepository;
import com.userservice.userservice.response.UsersResponse;

@Service
public class UsersServices {
	
	@Autowired
	UsersRepository usersRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	public UsersResponse findUserDetails(int Id) {
        Optional<Users> data = usersRepo.findById(Id);
        UsersResponse userResponse = mapper.map(data, UsersResponse.class);
        return userResponse;
    }

}
