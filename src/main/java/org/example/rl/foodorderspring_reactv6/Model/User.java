package org.example.rl.foodorderspring_reactv6.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.rl.foodorderspring_reactv6.DTO.RestaurantDTO;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private USER_ROLE role;

    @JsonIgnore /* fetching all the orders will separate api to fetch user object*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer") //create aud table for mapping
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    private List<RestaurantDTO>favorites = new ArrayList<>();

    /*list of addresses store in the user obj
    reduces the time to inputting adj when
    ordering food*/
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)/*auto removes users address after
    remove of user from db*/
    private List<Address> addresses = new ArrayList<>();

}
