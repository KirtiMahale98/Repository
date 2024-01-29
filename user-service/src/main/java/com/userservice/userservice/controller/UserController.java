package com.userservice.userservice.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.userservice.userservice.response.HotelsResponse;
import com.userservice.userservice.response.ReservationsResposne;
import com.userservice.userservice.response.UsersResponse;
import com.userservice.userservice.service.UserClient;
import com.userservice.userservice.services.UsersServices;

@RestController
public class UserController {

	@Autowired
	UserClient userClient;

	@Autowired
	UsersServices usersService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/getAllReservations/{userId}")
	public ResponseEntity<ReservationsResposne> getBothDetails(@PathVariable int userId) {
		UsersResponse data = usersService.findUserDetails(userId);		
		ReservationsResposne response = mapper.map(data, ReservationsResposne.class);
		HotelsResponse hoteldetails = userClient.getHotelDetails(userId);
		response.setHotels(hoteldetails);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/getAllReservationss/{userId}")
	public ResponseEntity<HotelsResponse> getHotelsById(@PathVariable int userId) {
		WebClient client = WebClient.builder()
				  .baseUrl("http://localhost:8080")
				  .build();
		HotelsResponse response = client.get().uri("/getHotelDetails/"+userId )
				.retrieve().bodyToMono(HotelsResponse.class).block();
		return ResponseEntity.status(HttpStatus.OK).body(response);
    }
	
	@GetMapping("/getUserDetails/{userId}")
	public ResponseEntity<UsersResponse>  getUserDetails(@PathVariable int userId) {
		UsersResponse data = usersService.findUserDetails(userId);		
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

}
