package com.foodCatelogue.foodCatalogue.mapper;


import com.foodCatelogue.foodCatalogue.dto.FoodItemDTO;
import com.foodCatelogue.foodCatalogue.entity.FoodItem;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {
    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItemDTO mapFoodItemTOFoodItemDTO(FoodItem fooditem);
    FoodItem mapFoodItemDTOToFoodItem(FoodItemDTO fooditemDTO);
}
