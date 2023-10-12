package com.example.FoodDeliveryApp.Dto.Response;

import com.example.FoodDeliveryApp.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse {

    String dishName;

    double price;

    FoodCategory category;

    boolean veg;
}
