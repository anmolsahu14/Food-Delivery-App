package com.example.FoodDeliveryApp.Controller;

import com.example.FoodDeliveryApp.Dto.Request.MenuRequest;
import com.example.FoodDeliveryApp.Dto.Request.RestaurantRequest;
import com.example.FoodDeliveryApp.Dto.Response.RestaurantResponse;
import com.example.FoodDeliveryApp.Service.CustomerService;
import com.example.FoodDeliveryApp.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, CustomerService customerService) {
        this.restaurantService = restaurantService;

    }


    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest){

        RestaurantResponse restaurantResponse = restaurantService.addRestaurant(restaurantRequest);
        return new ResponseEntity(restaurantResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/status")
    public ResponseEntity changeOpenedStatus(@RequestParam int id){

        String message = restaurantService.changeOpenedStatus(id);
        return new ResponseEntity(message,HttpStatus.ACCEPTED);

    }

    @PostMapping("/add/food")
    public ResponseEntity addMenuItemToRestaurant(@RequestBody MenuRequest menuRequest){

        RestaurantResponse restaurantResponse = restaurantService.addMenuItemToRestaurant(menuRequest);
        return new ResponseEntity(restaurantResponse,HttpStatus.CREATED);

    }

}
