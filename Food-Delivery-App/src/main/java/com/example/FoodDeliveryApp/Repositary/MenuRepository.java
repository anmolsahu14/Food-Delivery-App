package com.example.FoodDeliveryApp.Repositary;

import com.example.FoodDeliveryApp.Model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem,Integer> {


}
