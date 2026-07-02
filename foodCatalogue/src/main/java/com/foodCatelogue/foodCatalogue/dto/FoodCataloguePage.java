package com.foodCatelogue.foodCatalogue.dto;

import com.foodCatelogue.foodCatalogue.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCataloguePage {
    private List<FoodItem> foodItemList;
    private Restaurant restaurant;
}
