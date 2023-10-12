package com.example.FoodDeliveryApp.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartStatusResponse {

    String customerName;

    String customerAddress;

    String customerMobile;

    double cartTotal;

    List<FoodResponse> foodList;

    String restaurantName;

}
