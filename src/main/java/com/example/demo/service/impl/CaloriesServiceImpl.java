package com.example.demo.service.impl;

import com.example.demo.dto.CaloriesDto;
import com.example.demo.dto.CaloriesIngredientDto;
import com.example.demo.dto.IngredientsDto;
import com.example.demo.entity.Food;
import com.example.demo.entity.Ingredients;
import com.example.demo.repository.CaloriesRepository;
import com.example.demo.service.CaloriesService;
import com.example.demo.util.FoodCalculations;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class CaloriesServiceImpl implements CaloriesService {


    private final CaloriesRepository repository;

    @Override
    public CaloriesDto getCaloriesTotal(Food food) {
        Double calories = 0.0;
        List<Ingredients> ingredients = getIngredientsFood(food);

        calories = FoodCalculations.getCalories(ingredients,food);

        CaloriesDto caloriesDto = new CaloriesDto(calories);

        return caloriesDto;
    }

    @Override
    public CaloriesIngredientDto getCaloriesForIngredient(Food food) {
        List<Ingredients> ingredients = getIngredientsFood(food);

        CaloriesIngredientDto response = FoodCalculations.getCaloriesForIngredient(ingredients,food);

        return response;
    }

    @Override
    public IngredientsDto getCaloriesMax(Food food) {
        List<Ingredients> ingredients = getIngredientsFood(food);
        CaloriesIngredientDto ingredientDto = FoodCalculations.getCaloriesForIngredient(ingredients,food);
        Double max = Double.MIN_NORMAL;
        String nameMax = "";
        for (IngredientsDto ingredientsDto: ingredientDto.getIngredientsDtos()) {
            if (max < ingredientsDto.getCalories()){
                nameMax = ingredientsDto.getName();
                max = ingredientsDto.getCalories();
            }

        }
        IngredientsDto ingredientsDto = new IngredientsDto(nameMax,max);
        return ingredientsDto;
    }


    private List<Ingredients> getIngredientsFood(Food food){
        List<Ingredients> ingredients = new ArrayList<>();
        List<Ingredients> ingredientsList = repository.getIngredients();

        for (int i = 0 ; i < food.getIngredientsList().size() ; i++){
            String name = food.getIngredientsList().get(i).getName();

            if(ingredientsList.stream().filter(x -> x.getName().equals(name)).findFirst().isPresent()){
                ingredients.add(ingredientsList.stream().filter(z -> z.getName().equals(name)).findFirst().get());
            }
        }

        return ingredients;
    }
}
