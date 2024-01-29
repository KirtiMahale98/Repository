package com.hotelservice.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelservice.entity.Hotels;
import com.hotelservice.repository.HotelRepository;
import com.hotelservice.response.HotelsResponse;

@Service
public class HotelServices {
	
	@Autowired
	HotelRepository hotelRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	public HotelsResponse findHotelById(int userid) {
		Optional<Hotels> data  = hotelRepo.findHotelsByUserId(userid);
		HotelsResponse response = mapper.map(data,HotelsResponse.class);
		
		return response;
	}

}
