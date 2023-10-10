package com.example.FoodDeliveryApp.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    String name;
    String mobileNo;
    String address;
    CartResponse cart;

}
