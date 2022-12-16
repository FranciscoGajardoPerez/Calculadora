package com.example.demo.controller;

import com.example.demo.entity.Food;
import com.example.demo.service.impl.CaloriesServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("calories/v1")
@AllArgsConstructor
public class CaloriesController {


    private final CaloriesServiceImpl service;

    @PostMapping("/getcalories")
    public ResponseEntity getCaloriesTotal(@RequestBody Food food){
        return new ResponseEntity(service.getCaloriesTotal(food), HttpStatus.OK);
    }

    @PostMapping("/getcalories-x-ingredients")
    public ResponseEntity getCaloriesForIngredient(@RequestBody Food food){
        return new ResponseEntity(service.getCaloriesForIngredient(food), HttpStatus.OK);
    }

    @PostMapping("/getcaloriesmax")
    public ResponseEntity getCaloriesMax(@RequestBody Food food){
        return new ResponseEntity(service.getCaloriesMax(food),HttpStatus.OK);
    }
}
