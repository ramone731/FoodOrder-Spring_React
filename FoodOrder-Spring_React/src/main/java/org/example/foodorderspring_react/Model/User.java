package org.example.foodorderspring_react.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.foodorderspring_react.DTO.RestaurantDTO;

import java.util.ArrayList;
import java.util.List;

/*map our java class to our database table*/
@Entity
@Data /*for getter and setters*/
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private USER_ROLE role;

    @JsonIgnore
    @OneToMany
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    private List<RestaurantDTO>favorties = new ArrayList();

    @OneToMany
    private List<Address> addresses = new ArrayList<>();
}
