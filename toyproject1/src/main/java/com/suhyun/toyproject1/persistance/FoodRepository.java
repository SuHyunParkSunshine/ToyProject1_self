package com.suhyun.toyproject1.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suhyun.toyproject1.domain.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    
}
