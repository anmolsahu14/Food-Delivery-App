package com.example.FoodDeliveryApp.Transformer;

import com.example.FoodDeliveryApp.Dto.Request.RestaurantRequest;
import com.example.FoodDeliveryApp.Dto.Response.FoodResponse;
import com.example.FoodDeliveryApp.Dto.Response.RestaurantResponse;
import com.example.FoodDeliveryApp.Model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantTransformer {


    public static Restaurant RestaurantRequestToRestaurant(RestaurantRequest restaurantRequest){

        return Restaurant.builder()
                .name(restaurantRequest.getName())
                .contactNumber(restaurantRequest.getContactNumber())
                .location(restaurantRequest.getLocation())
                .restaurantCategory(restaurantRequest.getRestaurantCategory())
                .opened(true)
                .availableFoodItems(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();

    }

    public static RestaurantResponse RestaurantToRestaurantResponse(Restaurant restaurant){

        List<FoodResponse> menu = restaurant.getAvailableFoodItems()
                .stream()
                .map(foodItem -> FoodItemTransformer.FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());

        return RestaurantResponse.builder()
                .name(restaurant.getName())
                .contactNumber(restaurant.getContactNumber())
                .location(restaurant.getLocation())
                .opened(restaurant.isOpened())
                .menu(menu)
                .build();
    }
}
