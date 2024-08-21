package com.kcc.mission.mapper;

import com.kcc.mission.bean.Restaurant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    public List<Restaurant> getAllRestaurants();
    public Restaurant getOneRestaurant(Long id);
    public void registerRestaurant(Restaurant restaurant);
    public void updateRestaurant(Restaurant restaurant);
    public void deleteRestaurant(long id);
}