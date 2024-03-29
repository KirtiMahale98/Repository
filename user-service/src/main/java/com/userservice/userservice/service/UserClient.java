package com.userservice.userservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.userservice.userservice.response.HotelsResponse;

@FeignClient(name = "hotel-service", path = "/api/hotel")
public interface UserClient {
 
    @GetMapping("/getHotelDetails/{userid}")
    public HotelsResponse getHotelDetails(@PathVariable("userid") int userid);
 
}