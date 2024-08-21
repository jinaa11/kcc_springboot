package com.kcc.mission.controller;

import com.kcc.mission.bean.Restaurant;
import com.kcc.mission.service.RestaurantService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class RestaurantController {
    private RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() {
        return service.getAllRestaurants();
    }

    @GetMapping("/restaurant/{restaurantId}")
    public Restaurant getOneRestaurant(@PathVariable Long restaurantId) {
        return service.getOneRestaurant(restaurantId);
    }

    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        service.registerRestaurant(restaurant);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(restaurant.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/restaurant/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable long restaurantId, @RequestBody Restaurant restaurant) {
        service.updateRestaurant(restaurantId, restaurant);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public void deleteRestaurant(@PathVariable long restaurantId) {
        service.deleteRestaurant(restaurantId);
    }
}
