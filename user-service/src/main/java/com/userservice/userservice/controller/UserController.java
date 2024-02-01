package com.userservice.userservice.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.userservice.response.ReservationsResponse;
import com.userservice.userservice.response.UsersResponse;
import com.userservice.userservice.services.UsersServices;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class UserController {

	@Autowired
	UsersServices usersService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/getAllReservations/{userId}")
	@CircuitBreaker(name="hotelserviceBreaker", fallbackMethod =  "hotelFallbackMethod")
	public ResponseEntity<ReservationsResponse> getBothDetails(@PathVariable int userId) {
		UsersResponse data = usersService.findUserDetails(userId);
		ReservationsResponse response = mapper.map(data, ReservationsResponse.class);
		response.setHotels(usersService.getHotelDetails(userId));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/getAllReservations1/{userId}")
	@Bulkhead(name="hotelserviceBulkhead", type = Bulkhead.Type.SEMAPHORE)
	public ResponseEntity<ReservationsResponse> getBothDetails1(@PathVariable int userId) {
		UsersResponse data = usersService.findUserDetails(userId);
		ReservationsResponse response = mapper.map(data, ReservationsResponse.class);
		response.setHotels(usersService.getHotelDetails(userId));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	public ResponseEntity<ReservationsResponse> hotelFallbackMethod(int userId,Exception ex) {
		System.out.print("Exception during Hotel Service call "+ex.getMessage());
		ReservationsResponse reservations = new ReservationsResponse();
		reservations.setId(userId);reservations.setName("HotelService is not working");
		return new ResponseEntity<>(reservations,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getUserDetails/{userId}")
	public ResponseEntity<UsersResponse> getUserDetails(@PathVariable int userId) {
		UsersResponse data = usersService.findUserDetails(userId);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

}
