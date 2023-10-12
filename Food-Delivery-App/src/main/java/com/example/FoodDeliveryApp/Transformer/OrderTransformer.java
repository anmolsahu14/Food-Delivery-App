package com.example.FoodDeliveryApp.Transformer;

import com.example.FoodDeliveryApp.Dto.Response.FoodResponse;
import com.example.FoodDeliveryApp.Dto.Response.OrderResponse;
import com.example.FoodDeliveryApp.Model.OrderEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderTransformer {

    public static OrderEntity prepareOrderEntity(Cart cart){

        return OrderEntity.builder()
                .orderId(String.valueOf(UUID.randomUUID()))
                .orderTotal(cart.getCartTotal())
                .build();
    }

    public static OrderResponse OrderToOrderResponse(OrderEntity savedOrder) {

        List<FoodResponse> foodResponseList = savedOrder.getFoodItems()
                .stream()
                .map(foodItem -> FoodItemTransformer.FoodItemToFoodResponse(foodItem.getMenuItem())
                .collect(Collectors.toList())



        return OrderResponse.builder()
                .orderId(savedOrder.getOrderId())
                .orderTime(savedOrder.getOrderTime())
                .orderTotal(savedOrder.getOrderTotal())
                .customerName(savedOrder.getCustomer().getName())
                .customerMobile(savedOrder.getCustomer().getMobileNo())
                .deliveryPartnerName(savedOrder.getDeliveryPartner().getName())
                .deliveryPartnerMobile(savedOrder.getDeliveryPartner().getMobileNo())
                .restaurantName(savedOrder.getRestaurants().getName())
                .build();
    }
}
