package com.suhyun.toyproject1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suhyun.toyproject1.domain.Food;
import com.suhyun.toyproject1.persistance.FoodRepository;

@RestController
public class FoodController {

    @Autowired
    private FoodRepository foodRepo;

    @GetMapping("/getFoods")
    public Iterable<Food> getFoods() {
        return foodRepo.findAll();
    }
    
}
