package com.kcc.mission.service;

import com.kcc.mission.bean.Menu;
import com.kcc.mission.bean.Restaurant;
import com.kcc.mission.mapper.MenuMapper;
import com.kcc.mission.mapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantMapper mapper;
    @Autowired
    private MenuMapper menuMapper;

    public List<Restaurant> getAllRestaurants() {
        return mapper.getAllRestaurants();
    }

    public Restaurant registerRestaurant(Restaurant restaurant) {
        mapper.registerRestaurant(restaurant);

        for(Menu menu : restaurant.getMenus()) {
            menuMapper.insertMenu(menu);
        }
        return restaurant;
    }

    public Restaurant getOneRestaurant(Long id) {
        return mapper.getOneRestaurant(id);
    }

    public void updateRestaurant(long id, Restaurant restaurant) {
        restaurant.setId(id);
        mapper.updateRestaurant(restaurant);
    }

    public void deleteRestaurant(long id) {
        mapper.deleteRestaurant(id);
    }
}
