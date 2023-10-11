package com.example.FoodDeliveryApp.Service;

import com.example.FoodDeliveryApp.Dto.Request.FoodRequest;
import com.example.FoodDeliveryApp.Dto.Request.RestaurantRequest;
import com.example.FoodDeliveryApp.Dto.Response.RestaurantResponse;
import com.example.FoodDeliveryApp.Model.FoodItem;
import com.example.FoodDeliveryApp.Model.Restaurant;
import com.example.FoodDeliveryApp.Repositary.RestaurantRepository;
import com.example.FoodDeliveryApp.Transformer.FoodItemTransformer;
import com.example.FoodDeliveryApp.Transformer.RestaurantTransformer;
import com.example.FoodDeliveryApp.exceptions.RestaurantNotFoundException;
import com.example.FoodDeliveryApp.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {

     final RestaurantRepository restaurantRepository;
     final ValidationUtils validationUtils;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, ValidationUtils validationUtils) {
        this.restaurantRepository = restaurantRepository;
        this.validationUtils = validationUtils;
    }

    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {

        Restaurant restaurant = RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);

    }

    public String changeOpenedStatus(int id) {

        if(!validationUtils.validateRestaurantId(id)){
            throw new RestaurantNotFoundException("Restaurant does not Exist!!!!");
        }
        Restaurant restaurant = restaurantRepository.findById(id).get();

//        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
//        if(restaurantOptional.isEmpty()){
//            throw new RestaurantNotFoundException("Restaurant doesnot exits!!!");
//        }

//        Restaurant restaurant = restaurantOptional.get();
        restaurant.setOpened(!restaurant.isOpened());
        restaurantRepository.save(restaurant);

        if(restaurant.isOpened()){
            return "Restaurant is opened now!!!!";
        }
        return "Restaurant is closed!!!";
    }

    public RestaurantResponse addFoodItemToRestaurant(FoodRequest foodRequest) {

//        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(foodRequest.getRestaurantId());
//
//        if(restaurantOptional.isEmpty()){
//            throw new RestaurantNotFoundException("Restaurant does not Exist!!!!");
//        }

        if(!validationUtils.validateRestaurantId(foodRequest.getRestaurantId())){

            throw new RestaurantNotFoundException("Restaurant does not exist!!!!");
        }

        Restaurant restaurant = restaurantRepository.findById(foodRequest.getRestaurantId()).get();

        FoodItem foodItem = FoodItemTransformer.FoodRequestToFoodItem(foodRequest);

        foodItem.setRestaurants(restaurant);

        restaurant.getAvailableFoodItems().add(foodItem);

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);

    }
}
