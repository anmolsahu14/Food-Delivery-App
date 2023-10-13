package com.example.FoodDeliveryApp.Service;

import com.example.FoodDeliveryApp.Dto.Request.FoodRequest;
import com.example.FoodDeliveryApp.Dto.Response.CartStatusResponse;
import com.example.FoodDeliveryApp.Dto.Response.FoodResponse;
import com.example.FoodDeliveryApp.Model.Cart;
import com.example.FoodDeliveryApp.Model.Customer;
import com.example.FoodDeliveryApp.Model.FoodItem;
import com.example.FoodDeliveryApp.Model.MenuItem;
import com.example.FoodDeliveryApp.Repositary.CartRepository;
import com.example.FoodDeliveryApp.Repositary.CustomerRepository;
import com.example.FoodDeliveryApp.Repositary.FoodRepo;
import com.example.FoodDeliveryApp.Repositary.MenuRepository;
import com.example.FoodDeliveryApp.exceptions.CustomerNotFoundException;
import com.example.FoodDeliveryApp.exceptions.MenuItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {


    final CustomerRepository customerRepository;

    final MenuRepository menuRepository;

    final FoodRepo foodRepo;

    final CartRepository cartRepository;

    @Autowired
    public CartService(CustomerRepository customerRepository,
                       MenuRepository menuRepository,
                       FoodRepo foodRepo,
                       CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.menuRepository = menuRepository;
        this.foodRepo = foodRepo;
        this.cartRepository = cartRepository;
    }

    public CartStatusResponse addFoodItemToCart(FoodRequest foodRequest) {


        Customer customer = customerRepository.findByMobileNo(foodRequest.getCustomerMobile());

        if (customer == null) {
            throw new CustomerNotFoundException("Customer doesnit exist!!!!");
        }

        Optional<MenuItem> menuItemOptional = menuRepository.findById(foodRequest.getMenuItemId());

        if (menuItemOptional.isEmpty()) {
            throw new MenuItemNotFoundException("Item not available in the restaurant!!!");
        }

        MenuItem menuItem = menuItemOptional.get();

        if (!menuItem.isAvailable()) {
            throw new MenuItemNotFoundException("Given dish is out of stock for now!!!");
        }
        Cart cart = customer.getCart();

        boolean alreadyExists = false;

        FoodItem savedFoodItem = new FoodItem();

        if(cart.getFoodItems().size() != 0){
            for(FoodItem foodItem : cart.getFoodItems()){
                if(foodItem.getMenuItem().getId() == menuItem.getId()){
                    savedFoodItem = foodItem;
                    int curr = foodItem.getRequiredQuantity();
                    foodItem.setRequiredQuantity(curr + foodRequest.getRequiredQuantity());
                    alreadyExists = true;
                    break;
                }
            }
        }

        if(!alreadyExists) {
            FoodItem foodItem = FoodItem.builder()
                    .menuItem(menuItem)
                    .cart(customer.getCart())
                    .requiredQuantity(foodRequest.getRequiredQuantity())
                    .totalCost(foodRequest.getRequiredQuantity() * menuItem.getPrice())
                    .build();
             savedFoodItem = foodRepo.save(foodItem);

        }
        double cartTotal = 0;
        cart.getFoodItems().add(savedFoodItem);
        for(FoodItem food:cart.getFoodItems()){
            cartTotal += food.getRequiredQuantity() * food.getMenuItem().getPrice();
        }

        savedFoodItem.setCart(cart);
        cart.setCartTotal(cartTotal);
        menuItem.getFoodItems().add(savedFoodItem);

        Cart savedCart = cartRepository.save(cart);
        MenuItem savedMenuItem = menuRepository.save(menuItem);


        List<FoodResponse> foodResponseList = new ArrayList<>();

        for(FoodItem food : cart.getFoodItems()){
            FoodResponse foodResponse = FoodResponse.builder()
                    .dishName(food.getMenuItem().getDishName())
                    .price(food.getMenuItem().getPrice())
                    .category(food.getMenuItem().getCategory())
                    .veg(food.getMenuItem().isVeg())
                    .quantityAdded(food.getRequiredQuantity())
                    .build();

            foodResponseList.add(foodResponse);
        }

        return  CartStatusResponse.builder()
                .customerName(savedCart.getCustomer().getName())
                .customerMobile(savedCart.getCustomer().getMobileNo())
                .foodList(foodResponseList)
                .restaurantName(savedMenuItem.getRestaurants().getName())
                .cartTotal(cartTotal)
                .build();

    }
}
