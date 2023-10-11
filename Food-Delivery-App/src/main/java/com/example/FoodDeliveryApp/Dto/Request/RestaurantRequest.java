package com.example.FoodDeliveryApp.Dto.Request;

import com.example.FoodDeliveryApp.Enum.RestaurantCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequest {
    String name;
    String location;
    RestaurantCategory restaurantCategory;
    String contactNumber;
}
