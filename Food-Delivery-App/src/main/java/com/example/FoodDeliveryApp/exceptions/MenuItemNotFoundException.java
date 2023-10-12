package com.example.FoodDeliveryApp.exceptions;

public class MenuItemNotFoundException extends RuntimeException{

    public MenuItemNotFoundException(String message) {
        super(message);
    }


}
