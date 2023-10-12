package com.example.FoodDeliveryApp.Dto.Request;

import com.example.FoodDeliveryApp.Enum.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPartnerRequest {

    String name;

    String mobileNo;

    Gender gender;

}
