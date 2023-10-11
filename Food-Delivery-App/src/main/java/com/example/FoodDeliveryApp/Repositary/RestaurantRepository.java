package com.example.FoodDeliveryApp.Repositary;

import com.example.FoodDeliveryApp.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {


}
