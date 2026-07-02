package com.rajdeep.order.entity;

import com.rajdeep.order.dto.FoodItemsDTO;
import com.rajdeep.order.dto.RestaurantDTO;
import com.rajdeep.order.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private Integer orderId;
    private List<FoodItemsDTO> foodItemList;
    private RestaurantDTO restaurantDTO;
    private UserDTO userDTO;
}
