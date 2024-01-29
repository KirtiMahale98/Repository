package com.hotelservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hotelservice.response.HotelsResponse;
import com.hotelservice.services.HotelServices;

@RestController
public class HotelController {

	
	@Autowired
	HotelServices hotelService;
	
	@GetMapping("/getHotelDetails/{userid}")
	public ResponseEntity<HotelsResponse> getHotelDetails(@PathVariable int userid) {
		HotelsResponse data = hotelService.findHotelById(userid);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}
}
