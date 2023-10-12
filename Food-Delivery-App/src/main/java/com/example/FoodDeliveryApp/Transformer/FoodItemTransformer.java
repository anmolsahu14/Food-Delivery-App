package com.example.FoodDeliveryApp.Transformer;

import com.example.FoodDeliveryApp.Dto.Request.MenuRequest;
import com.example.FoodDeliveryApp.Dto.Response.MenuResponse;
import com.example.FoodDeliveryApp.Model.MenuItem;

public class FoodItemTransformer {

    public static MenuItem FoodRequestToFoodItem(MenuRequest menuRequest){
        return MenuItem.builder()
                .dishName(menuRequest.getDishName())
                .price(menuRequest.getPrice())
                .category(menuRequest.getCategory())
                .veg(menuRequest.isVeg())
                .available(menuRequest.isAvailable())
                .build();
    }

    public static MenuResponse FoodItemToFoodResponse(MenuItem fooditem){

        return MenuResponse.builder()
                .dishName(fooditem.getDishName())
                .price(fooditem.getPrice())
                .veg(fooditem.isVeg())
                .category(fooditem.getCategory())
                .build();
    }




}
