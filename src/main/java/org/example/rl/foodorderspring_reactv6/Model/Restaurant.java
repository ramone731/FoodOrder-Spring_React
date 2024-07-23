package org.example.rl.foodorderspring_reactv6.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User owner;
    private String name;
    private String description;
    private String cuisineType;
    @OneToOne
    private Address address;

    @Embedded
    private ContactInformation contactInformation;

    private String openingHours;
    /*mapped to the restaurant class => model controller,
    * cascade: deletes orders after users cancel an order*/
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    @Column(length = 1000)
    private List<String> images;

    private LocalDateTime registrationDate; //store user date/time of order
    private boolean open;

    @JsonIgnore //creates a separate api for fetching user food orders
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Food> food = new ArrayList<>();
}
