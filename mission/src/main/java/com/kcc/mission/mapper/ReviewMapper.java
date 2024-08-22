package com.kcc.mission.mapper;

import com.kcc.mission.bean.Review;
import com.kcc.mission.bean.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    public void registerReview(Review review);
    public ReviewDTO getReviewsByRestaurantId(long restaurant_id);
}
