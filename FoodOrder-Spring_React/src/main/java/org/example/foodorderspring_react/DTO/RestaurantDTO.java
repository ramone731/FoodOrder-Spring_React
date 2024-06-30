package org.example.foodorderspring_react.DTO;

import jakarta.persistence.Column;

import java.util.List;

public class RestaurantDTO {
    private String title;
    @Column(length = 1000)
    private List<String> images;

    private String description;
    private Long id;
}
