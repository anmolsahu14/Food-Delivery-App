package com.example.FoodDeliveryApp.Model;

import com.example.FoodDeliveryApp.Enum.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="menu_item")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String dishName;

    double price;

    @Enumerated(EnumType.STRING)
    FoodCategory category;

    boolean veg;

    @Getter
    boolean available;

    @ManyToOne
    @JoinColumn
    Restaurant restaurants;


    @OneToMany(mappedBy = "menuItem", cascade =  CascadeType.ALL)
    List<FoodItem> foodItems = new ArrayList<>();


}
