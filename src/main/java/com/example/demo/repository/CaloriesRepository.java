package com.example.demo.repository;

import com.example.demo.entity.Food;
import com.example.demo.entity.Ingredients;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CaloriesRepository {


    List<Ingredients> ingredientsList;

    public CaloriesRepository(){
        ingredientsList = getIngredients();
    }


    public List<Ingredients> getFood(){
        return this.ingredientsList;
    }

    public List<Ingredients> getIngredients(){
        List<Ingredients> ingredients = null;

        File file;
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false) //nueva
                .registerModule(new JavaTimeModule()); // nueva

        TypeReference<List<Ingredients>> typeRef = new TypeReference<>() {};

        try {
            file = ResourceUtils.getFile("classpath:json/ingredients.json");
            ingredients = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ingredients;
    }
}
