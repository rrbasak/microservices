package com.foodCatelogue.foodCatalogue.controller;


import com.foodCatelogue.foodCatalogue.dto.FoodCataloguePage;
import com.foodCatelogue.foodCatalogue.dto.FoodItemDTO;
import com.foodCatelogue.foodCatalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
public class FoodCatalogueController {


    @Autowired
    FoodCatalogueService foodItemservice;


    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO fooditemDTO){
       FoodItemDTO foodItemSaved = foodItemservice.addFoodItem(fooditemDTO);
       return new ResponseEntity<>(foodItemSaved, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{id}")
    public ResponseEntity<FoodCataloguePage> fetchrestaurantDetailsWithFoodMenu(@PathVariable Integer id){
        FoodCataloguePage foodCataloguePage = foodItemservice.fetchrestaurantDetailsWithFoodMenu(id);
        return new ResponseEntity<>(foodCataloguePage,HttpStatus.OK);
    }
}
