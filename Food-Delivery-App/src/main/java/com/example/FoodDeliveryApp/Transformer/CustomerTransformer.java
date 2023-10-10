package com.example.FoodDeliveryApp.Transformer;

import com.example.FoodDeliveryApp.Dto.Request.CustomerRequest;
import com.example.FoodDeliveryApp.Dto.Response.CartResponse;
import com.example.FoodDeliveryApp.Dto.Response.CustomerResponse;
import com.example.FoodDeliveryApp.Model.Customer;

public class CustomerTransformer {

    public static Customer CustomerRequestToCustomer(CustomerRequest customerRequest){

        return Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .address(customerRequest.getAddress())
                .mobileNo(customerRequest.getMobileNo())
                .gender(customerRequest.getGender())
                .build();
    }

    public static CustomerResponse CustomerToCustomerResponse(Customer customer){

        CartResponse cartResponse = CartTransformer.CartToCartResponse(customer.getCart());
        return CustomerResponse.builder()
                .name(customer.getName())
                .mobileNo(customer.getMobileNo())
                .address(customer.getAddress())
                .cart(cartResponse)
                .build();
    }
}
