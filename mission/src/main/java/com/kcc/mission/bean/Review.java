package com.kcc.mission.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private int id;
    private String content;
    private double score;
    private Date created_at;
    private int restaurant_id;
}
