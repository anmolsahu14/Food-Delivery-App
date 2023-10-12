package com.example.FoodDeliveryApp.Transformer;

import com.example.FoodDeliveryApp.Dto.Response.CartResponse;
import com.example.FoodDeliveryApp.Model.Cart;

import java.util.ArrayList;

public class CartTransformer {

    public static CartResponse CartToCartResponse(Cart cart){

        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .foodItems(new ArrayList<>())
                .build();
    }

}
