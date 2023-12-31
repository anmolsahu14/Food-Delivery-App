package com.example.FoodDeliveryApp.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodRequest {

    int requiredQuantity;

    String customerMobile;

    int menuItemId;

}
