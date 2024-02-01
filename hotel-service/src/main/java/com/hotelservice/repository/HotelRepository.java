package com.hotelservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hotelservice.entity.Hotels;

public interface HotelRepository extends JpaRepository<Hotels, Integer> {
	@Query(nativeQuery = true, value = "select h.* from hotels h, users u where h.userid = :inputId and u.id = h.userid")
	Optional<Hotels> findHotelsByUserId(@Param("inputId") int inputId);

}
