package com.example.FoodDeliveryApp.Controller;

import com.example.FoodDeliveryApp.Dto.Response.OrderResponse;
import com.example.FoodDeliveryApp.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place/mobile/{mobile}")
    public OrderResponse placeOrder(@PathVariable("mobile") String customerMobile){

        OrderResponse orderResponse = orderService.placeOrder(customerMobile);



    }
}
