package com.example.FoodDeliveryApp.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

    double cartTotal;

    List<MenuResponse> foodItems;

}
