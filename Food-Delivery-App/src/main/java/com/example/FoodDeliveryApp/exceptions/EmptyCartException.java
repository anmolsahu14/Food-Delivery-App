package com.example.FoodDeliveryApp.exceptions;

public class EmptyCartException  extends  RuntimeException{

    public EmptyCartException(String message){
        super(message);
    }
}
