package com.example.FoodDeliveryApp.Repositary;

import com.example.FoodDeliveryApp.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface OrderEntityRepo extends JpaRepository<OrderEntity,Integer> {



}
