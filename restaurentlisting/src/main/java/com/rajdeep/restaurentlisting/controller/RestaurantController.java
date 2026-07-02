package com.rajdeep.restaurentlisting.controller;


import com.rajdeep.restaurentlisting.dto.RestaurantDTO;
import com.rajdeep.restaurentlisting.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;


    @GetMapping("/fetchAllRestaurant")
    public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurant(){
        List<RestaurantDTO> allRestaurant = restaurantService.findAllRestaurant();
        return new ResponseEntity<>(allRestaurant, HttpStatus.OK);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        System.out.println("Reached Controller");
        System.out.println(restaurantDTO);
        RestaurantDTO restaurantAdded = restaurantService.saveRestaurantInDB(restaurantDTO);
        return new ResponseEntity<>(restaurantAdded, HttpStatus.CREATED);
    }

    @GetMapping("/fetchById/{id}")
    public ResponseEntity<RestaurantDTO> findRestaurantById(@PathVariable Integer id){
        return restaurantService.fetchRestaurantById(id);
    }




}
