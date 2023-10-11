package com.example.FoodDeliveryApp.Transformer;

import com.example.FoodDeliveryApp.Dto.Request.FoodRequest;
import com.example.FoodDeliveryApp.Dto.Response.FoodResponse;
import com.example.FoodDeliveryApp.Model.FoodItem;

public class FoodItemTransformer {

    public static FoodItem FoodRequestToFoodItem(FoodRequest foodRequest){
        return FoodItem.builder()
                .dishName(foodRequest.getDishName())
                .price(foodRequest.getPrice())
                .category(foodRequest.getCategory())
                .veg(foodRequest.isVeg())
                .available(foodRequest.isAvailable())
                .build();
    }

    public static FoodResponse FoodItemToFoodResponse(FoodItem fooditem){

        return FoodResponse.builder()
                .dishName(fooditem.getDishName())
                .price(fooditem.getPrice())
                .veg(fooditem.isVeg())
                .category(fooditem.getCategory())
                .build();
    }




}
