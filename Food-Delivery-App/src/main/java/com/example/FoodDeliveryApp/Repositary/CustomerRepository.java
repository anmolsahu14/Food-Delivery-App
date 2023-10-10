package com.example.FoodDeliveryApp.Repositary;

import com.example.FoodDeliveryApp.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {


    public Customer findByMobileNo(String mobile);
}
