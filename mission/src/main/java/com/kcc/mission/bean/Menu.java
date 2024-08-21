package com.kcc.mission.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Long id;
    private String name;
    private int price;
    private Date created_at;
    private Date updated_at;
    private int restaurant_id;
}
