package org.example.rl.foodorderspring_reactv6.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Food food;
    private int quantity;
    private Long totalPrice;

    @ElementCollection /*syntax - Basic attribute should not be a container*/
    private List<String> ingredients;
}
