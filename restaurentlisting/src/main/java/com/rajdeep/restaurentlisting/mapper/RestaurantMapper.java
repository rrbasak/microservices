package com.rajdeep.restaurentlisting.mapper;


import com.rajdeep.restaurentlisting.dto.RestaurantDTO;
import com.rajdeep.restaurentlisting.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant mapRestaurantDTOTORestaurant(RestaurantDTO restaurantdto);
    RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);
}
