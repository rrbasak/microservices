package com.rajdeep.restaurentlisting.service;


import com.rajdeep.restaurentlisting.dto.RestaurantDTO;
import com.rajdeep.restaurentlisting.entity.Restaurant;
import com.rajdeep.restaurentlisting.mapper.RestaurantMapper;
import com.rajdeep.restaurentlisting.repo.RestaurantRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepo restaurantRepo;

    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    public void testFindAllRestaurant(){
        List<Restaurant> allMockRestaurant = Arrays.asList(
                new Restaurant(1, "KFC", "Address 1", "Delhi", "Fast Food"),
                new Restaurant(2, "Dominos", "Address 2", "Mumbai", "Pizza")
        );

        when(restaurantRepo.findAll()).thenReturn(allMockRestaurant);
        List<RestaurantDTO> restaurantDTOList = restaurantService.findAllRestaurant();


        assertEquals(allMockRestaurant.size(),restaurantDTOList.size());
        for(int i = 0; i < allMockRestaurant.size(); i++){
            RestaurantDTO expectedDTO = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(allMockRestaurant.get(i));
            assertEquals(expectedDTO,restaurantDTOList.get(i));
        }

        verify(restaurantRepo,times(1)).findAll();

    }

    @Test
    public void testSaveRestaurantInDB(){
        Restaurant mockRestaurant = new Restaurant(1, "KFC", "Address 1", "Delhi", "Fast Food");
        RestaurantDTO mockRestaurantDTO = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(mockRestaurant);

        when(restaurantRepo.save(mockRestaurant)).thenReturn(mockRestaurant);
        RestaurantDTO savedRestaurantDTO = restaurantService.saveRestaurantInDB(mockRestaurantDTO);

        assertEquals(mockRestaurantDTO,savedRestaurantDTO);
        verify(restaurantRepo,times(1)).save(mockRestaurant);
    }
    @Test
    public void testFetchRestaurantById(){
        Integer mockRestaurantId = 1;
        Restaurant mockRestaurant = new Restaurant(1, "KFC", "Address 1", "Delhi", "Fast Food");


        when(restaurantRepo.findById(mockRestaurantId)).thenReturn(Optional.of(mockRestaurant));
        ResponseEntity<RestaurantDTO> response = restaurantService.fetchRestaurantById(mockRestaurantId);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mockRestaurantId,response.getBody().getId());
        verify(restaurantRepo,times(1)).findById(mockRestaurantId);

    }

    @Test
    public void testFetchRestaurantById_NonExtingId(){
        Integer mockRestaurantId = 1;
        Restaurant mockRestaurant = new Restaurant(1, "KFC", "Address 1", "Delhi", "Fast Food");


        when(restaurantRepo.findById(mockRestaurantId)).thenReturn(Optional.empty());
        ResponseEntity<RestaurantDTO> response = restaurantService.fetchRestaurantById(mockRestaurantId);

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals(null,response.getBody());
        verify(restaurantRepo,times(1)).findById(mockRestaurantId);

    }

}
