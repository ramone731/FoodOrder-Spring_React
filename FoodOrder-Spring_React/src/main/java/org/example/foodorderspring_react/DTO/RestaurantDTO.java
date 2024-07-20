package org.example.rl.foodorderspring_reactv6.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RestaurantDTO {
    private String title;

    @Column(length = 1000)
    @ElementCollection /*syntax - Basic attribute should not be a container*/
    private List<String> image;

    private String description;
    private Long id;
}
