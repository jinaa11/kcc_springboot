package com.kcc.mission.service;

import com.kcc.mission.bean.Review;
import com.kcc.mission.bean.ReviewDTO;
import com.kcc.mission.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewMapper mapper;

    public void registerReview(Review review) {
        mapper.registerReview(review);
    }

    public ReviewDTO getReviewsByRestaurantId(long restaurant_id) {
        return mapper.getReviewsByRestaurantId(restaurant_id);

    }

}
