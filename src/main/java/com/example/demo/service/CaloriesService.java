package com.example.demo.service;

import com.example.demo.dto.CaloriesDto;
import com.example.demo.dto.CaloriesIngredientDto;
import com.example.demo.dto.IngredientsDto;
import com.example.demo.entity.Food;

public interface CaloriesService {

    CaloriesDto getCaloriesTotal(Food food);

    CaloriesIngredientDto getCaloriesForIngredient(Food food);

    IngredientsDto getCaloriesMax(Food food);
}
