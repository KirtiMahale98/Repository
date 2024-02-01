package com.userservice.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userservice.userservice.entity.Users;

public interface UsersRepository  extends JpaRepository<Users, Integer> {

}
