package com.rajdeep.restaurentlisting.service;


import com.netflix.discovery.converters.Auto;
import com.rajdeep.restaurentlisting.dto.RestaurantDTO;
import com.rajdeep.restaurentlisting.entity.Restaurant;
import com.rajdeep.restaurentlisting.mapper.RestaurantMapper;
import com.rajdeep.restaurentlisting.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepo restaurantRepo;


    public List<RestaurantDTO> findAllRestaurant(){
        List<Restaurant> restaurants = restaurantRepo.findAll();
        List<RestaurantDTO> restaurantsDTOList = restaurants.stream().map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant)).collect(Collectors.toList());
        return restaurantsDTOList;
    }
    public RestaurantDTO saveRestaurantInDB(RestaurantDTO restaurantDTO){
        Restaurant saveRestaurant = restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOTORestaurant(restaurantDTO));
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(saveRestaurant);

    }

    public ResponseEntity<RestaurantDTO> fetchRestaurantById(Integer id){
        Optional<Restaurant> restaurant = restaurantRepo.findById(id);
        if(restaurant.isPresent()){
            return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
