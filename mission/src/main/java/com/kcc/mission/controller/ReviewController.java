package com.kcc.mission.controller;

import com.kcc.mission.bean.Review;
import com.kcc.mission.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    private ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

//    @PostMapping("/review")
//    public ResponseEntity<Review> writeReview(@RequestBody Review review) {
//
//
//    }
}
