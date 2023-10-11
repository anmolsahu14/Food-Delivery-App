package com.example.FoodDeliveryApp.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponse {

    String name;

    String contactNumber;

    String location;

    boolean opened;

    List<FoodResponse> menu;

}
