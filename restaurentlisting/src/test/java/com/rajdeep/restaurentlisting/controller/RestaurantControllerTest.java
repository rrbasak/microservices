package com.rajdeep.restaurentlisting.controller;

import com.rajdeep.restaurentlisting.dto.RestaurantDTO;
import com.rajdeep.restaurentlisting.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;



    @Test
    public void testFetchAllRestaurant(){
        List<RestaurantDTO> allMockRestaurant = Arrays.asList(
                new RestaurantDTO(1, "KFC", "Address 1", "Delhi", "Fast Food"),
                new RestaurantDTO(2, "Dominos", "Address 2", "Mumbai", "Pizza")
        );

        when(restaurantService.findAllRestaurant()).thenReturn(allMockRestaurant);
        ResponseEntity<List<RestaurantDTO>> response = restaurantController.fetchAllRestaurant();

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(allMockRestaurant,response.getBody());

    }

    @Test
    public void testSaveRestaurant(){
        RestaurantDTO mockRestaurant =
                new RestaurantDTO(
                        1,
                        "KFC",
                        "Address 1",
                        "Delhi",
                        "Fast Food"
                );

        when(restaurantService.saveRestaurantInDB(mockRestaurant)).thenReturn(mockRestaurant);

        ResponseEntity<RestaurantDTO> response = restaurantController.saveRestaurant(mockRestaurant);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(mockRestaurant,response.getBody());
    }



    @Test
    public void testFindRestaurantById() {

        Integer mockRestaurantId  = 1;

        RestaurantDTO mockRestaurant =
                new RestaurantDTO(
                        1,
                        "Restaurant 1",
                        "Address 1",
                        "City 1",
                        "Description 1"
                );

        when(restaurantService.fetchRestaurantById(mockRestaurantId ))
                .thenReturn(new ResponseEntity<>(mockRestaurant, HttpStatus.OK));

        ResponseEntity<RestaurantDTO> response =
                restaurantController.findRestaurantById(mockRestaurantId );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurant, response.getBody());
    }

}