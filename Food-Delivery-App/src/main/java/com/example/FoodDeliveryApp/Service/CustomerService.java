package com.example.FoodDeliveryApp.Service;

import com.example.FoodDeliveryApp.Dto.Request.CustomerRequest;
import com.example.FoodDeliveryApp.Dto.Response.CustomerResponse;
import com.example.FoodDeliveryApp.Model.Cart;
import com.example.FoodDeliveryApp.Model.Customer;
import com.example.FoodDeliveryApp.Repositary.CustomerRepository;
import com.example.FoodDeliveryApp.Transformer.CustomerTransformer;
import com.example.FoodDeliveryApp.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        //dto to model
        Customer customer = CustomerTransformer.CustomerRequestToCustomer(customerRequest);

        //allocate a cart

        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerTransformer.CustomerToCustomerResponse(savedCustomer);
    }

    public CustomerResponse findCustomerByMobile(String mobile) {
        Customer customer = customerRepository.findByMobileNo(mobile);

        if(customer.equals(null)){
            throw new CustomerNotFoundException("Invalid mobile Number!!!!");
        }
        return CustomerTransformer.CustomerToCustomerResponse(customer);
    }
}
