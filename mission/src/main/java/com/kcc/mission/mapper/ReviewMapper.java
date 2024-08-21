package com.kcc.mission.mapper;

import com.kcc.mission.bean.Review;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    public void registerReview(Review review);
}
