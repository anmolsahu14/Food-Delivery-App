package com.example.FoodDeliveryApp.Service;

import com.example.FoodDeliveryApp.Dto.Response.OrderResponse;
import com.example.FoodDeliveryApp.Model.*;
import com.example.FoodDeliveryApp.Repositary.CustomerRepository;
import com.example.FoodDeliveryApp.Repositary.DeliveryPartnerRepo;
import com.example.FoodDeliveryApp.Repositary.OrderEntityRepo;
import com.example.FoodDeliveryApp.Repositary.RestaurantRepository;
import com.example.FoodDeliveryApp.Transformer.OrderTransformer;
import com.example.FoodDeliveryApp.exceptions.CustomerNotFoundException;
import com.example.FoodDeliveryApp.exceptions.EmptyCartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {
    final CustomerRepository customerRepository;
    final DeliveryPartnerRepo deliveryPartnerRepo;
    final OrderEntityRepo orderEntityRepo;
    final RestaurantRepository restaurantRepository;
    @Autowired
    public OrderService(CustomerRepository customerRepository,
                        DeliveryPartnerRepo deliveryPartnerRepo, OrderEntityRepo orderEntityRepo, RestaurantRepository restaurantRepository) {

        this.customerRepository = customerRepository;
        this.deliveryPartnerRepo = deliveryPartnerRepo;
        this.orderEntityRepo = orderEntityRepo;
        this.restaurantRepository = restaurantRepository;
    }

    public OrderResponse placeOrder(String customerMobile) {

        Customer customer = customerRepository.findByMobileNo(customerMobile);

        if(customer == null){
            throw new CustomerNotFoundException("Invalid Mobile Number!!!!");
        }

        Cart cart = customer.getCart();
        if(cart.getFoodItems().size()==0){
            throw new EmptyCartException("Sorry !!! your cart is empty!!!!");
        }

        DeliveryPartner partner = deliveryPartnerRepo.findRandomDeliveryPartner();
        Restaurant restaurant = cart.getFoodItems().get(0).getMenuItem().getRestaurants();

        OrderEntity order = OrderTransformer.prepareOrderEntity(cart);

        OrderEntity savedOrder = orderEntityRepo.save(order);

        order.setCustomer(customer);
        order.setDeliveryPartner(partner);
        order.setRestaurants(restaurant);
        order.setFoodItems(cart.getFoodItems());


        customer.getOrders().add(savedOrder);
        partner.getOrders().add(savedOrder);
        restaurant.getOrders().add(savedOrder);


        for(FoodItem foodItem : cart.getFoodItems()){

            foodItem.setCart(null);
            foodItem.setOrder(savedOrder);
        }

        clearCart(cart);

        customerRepository.save(customer);
        deliveryPartnerRepo.save(partner);
        restaurantRepository.save(restaurant);

        return OrderTransformer.OrderToOrderResponse(savedOrder);
    }

    private void clearCart(Cart cart){
        cart.setCartTotal(0);
        cart.setFoodItems(new ArrayList<>());
    }
}
