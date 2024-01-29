package com.example.eurekaclient.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.eurekaclient.entity.Users;

public interface UserRepository extends MongoRepository<Users,String>{ 
	  
}