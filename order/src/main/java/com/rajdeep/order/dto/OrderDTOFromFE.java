package com.rajdeep.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTOFromFE {
    private List<FoodItemsDTO> foodItemsDTOList;
    private RestaurantDTO restaurantDTO;
    private Integer userId;
}
