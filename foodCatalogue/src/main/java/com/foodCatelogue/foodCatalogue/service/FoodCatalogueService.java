package com.foodCatelogue.foodCatalogue.service;


import com.foodCatelogue.foodCatalogue.dto.FoodCataloguePage;
import com.foodCatelogue.foodCatalogue.dto.FoodItemDTO;
import com.foodCatelogue.foodCatalogue.dto.Restaurant;
import com.foodCatelogue.foodCatalogue.entity.FoodItem;
import com.foodCatelogue.foodCatalogue.mapper.FoodItemMapper;
import com.foodCatelogue.foodCatalogue.repo.FoodItemRepo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepo foodItemrepo;

    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO fooditemDTO) {
        FoodItem foodItemSavedInDb = foodItemrepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(fooditemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemTOFoodItemDTO(foodItemSavedInDb);
    }

    public FoodCataloguePage fetchrestaurantDetailsWithFoodMenu(Integer restaurantid) {


        //food item list
        List<FoodItem> fooditemlist = fetchFoodItemList(restaurantid);
        // restaurant details
        Restaurant restaurant =  fetchRestaurantDetailsFromRestaurantMicroService(restaurantid);

        return createFoodCataloguePage(fooditemlist,restaurant);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> fooditemlist, Restaurant restaurant) {

        FoodCataloguePage foodcataloguePage = new FoodCataloguePage();
        foodcataloguePage.setFoodItemList(fooditemlist);
        foodcataloguePage.setRestaurant(restaurant);

        return foodcataloguePage;
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantid) {
        return foodItemrepo.findByRestaurantId(restaurantid);
    }
    private Restaurant fetchRestaurantDetailsFromRestaurantMicroService(Integer restaurantid) {
//        return restTemplate.getForObject("http://localhost:9091/restaurant/fetchById/"+restaurantid,Restaurant.class);
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantid,Restaurant.class);
    }
}
