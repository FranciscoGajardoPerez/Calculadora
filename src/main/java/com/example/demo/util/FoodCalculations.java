package com.example.demo.util;

import com.example.demo.dto.CaloriesIngredientDto;
import com.example.demo.dto.IngredientsDto;
import com.example.demo.entity.Food;
import com.example.demo.entity.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class FoodCalculations {

    public static Double getCalories(List<Ingredients> ingredients, Food food) {
        StringBuilder name = new StringBuilder();
        Double response = 0.0;
        Double kilo;
        Integer calories = 0;
        for (int i = 0 ; i < food.getIngredientsList().size() ; i++){
            name.append(food.getIngredientsList().get(i).getName());

            kilo = food.getIngredientsList().get(i).getGrams()/1000;

            calories = ingredients
                    .stream()
                    .filter(x -> x.getName().equals(name.toString()))
                    .findFirst()
                    .get()
                    .getCalories();
            response += calories * kilo;

            name.delete(0,name.length());
        }
        return response;
    }

    public static CaloriesIngredientDto getCaloriesForIngredient(List<Ingredients> ingredients, Food food) {
        StringBuilder name = new StringBuilder();
        List<IngredientsDto> ingredientsDtos = new ArrayList<>();
        Double kilo;
        Double caloriesTotals = 0.0;
        Integer calories;
        for (int i = 0 ; i < food.getIngredientsList().size() ; i++){
            name.append(food.getIngredientsList().get(i).getName());

            kilo = food.getIngredientsList().get(i).getGrams()/1000;

            calories = ingredients
                    .stream()
                    .filter(x -> x.getName().equals(name.toString()))
                    .findFirst()
                    .get()
                    .getCalories();
            caloriesTotals += calories * kilo;
            ingredientsDtos.add(new IngredientsDto(name.toString(),caloriesTotals));
            name.delete(0,name.length());
        }
        CaloriesIngredientDto ingredientsResponse = new CaloriesIngredientDto(ingredientsDtos);

        return ingredientsResponse;
    }

}
