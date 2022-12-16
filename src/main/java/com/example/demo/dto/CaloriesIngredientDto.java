package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CaloriesIngredientDto {
    private List<IngredientsDto> ingredientsDtos;

}
