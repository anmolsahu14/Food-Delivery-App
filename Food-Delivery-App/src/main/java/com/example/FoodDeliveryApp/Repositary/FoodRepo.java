package com.example.FoodDeliveryApp.Repositary;

import com.example.FoodDeliveryApp.Model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepo extends JpaRepository<FoodItem,Integer> {


}
