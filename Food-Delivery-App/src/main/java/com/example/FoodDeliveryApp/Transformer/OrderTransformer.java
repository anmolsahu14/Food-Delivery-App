package com.example.FoodDeliveryApp.Transformer;

import com.example.FoodDeliveryApp.Dto.Response.FoodResponse;
import com.example.FoodDeliveryApp.Dto.Response.MenuResponse;
import com.example.FoodDeliveryApp.Dto.Response.OrderResponse;
import com.example.FoodDeliveryApp.Model.FoodItem;
import com.example.FoodDeliveryApp.Model.MenuItem;
import com.example.FoodDeliveryApp.Model.OrderEntity;
import com.example.FoodDeliveryApp.Model.Cart;

import java.util.ArrayList;
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

        List<FoodResponse> foodResponseList = new ArrayList<>();
        for(FoodItem food : savedOrder.getFoodItems()){
            FoodResponse foodResponse = FoodResponse.builder()
                    .dishName(food.getMenuItem().getDishName())
                    .price(food.getMenuItem().getPrice())
                    .category(food.getMenuItem().getCategory())
                    .veg(food.getMenuItem().isVeg())
                    .quantityAdded(food.getRequiredQuantity())
                    .build();

            foodResponseList.add(foodResponse);
        }




        return OrderResponse.builder()
                .orderId(savedOrder.getOrderId())
                .orderTime(savedOrder.getOrderTime())
                .orderTotal(savedOrder.getOrderTotal())
                .customerName(savedOrder.getCustomer().getName())
                .customerMobile(savedOrder.getCustomer().getMobileNo())
                .deliveryPartnerName(savedOrder.getDeliveryPartner().getName())
                .deliveryPartnerMobile(savedOrder.getDeliveryPartner().getMobileNo())
                .restaurantName(savedOrder.getRestaurants().getName())
                .foodResponses(foodResponseList)
                .build();
    }
}
