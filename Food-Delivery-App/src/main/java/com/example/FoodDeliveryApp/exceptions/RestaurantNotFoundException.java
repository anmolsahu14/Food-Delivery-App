package com.example.FoodDeliveryApp.exceptions;

public class RestaurantNotFoundException extends RuntimeException{

    public RestaurantNotFoundException(String message){

        super(message);
    }
}
