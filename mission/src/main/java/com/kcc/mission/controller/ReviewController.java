package com.kcc.mission.controller;

import com.kcc.mission.bean.Review;
import com.kcc.mission.bean.ReviewDTO;
import com.kcc.mission.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ReviewController {
    private ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping("/review")
    public ResponseEntity<Review> writeReview(@RequestBody Review review) {
        service.registerReview(review);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(review.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/restaurant/{restaurantId}/reviews")
    public ReviewDTO getReviewsByRestaurantId(@PathVariable long restaurantId) {
        return service.getReviewsByRestaurantId(restaurantId);
    }
}
