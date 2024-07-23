package org.example.rl.foodorderspring_reactv6.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private String description;
  private Long price;

  /*
*  @ManyToOne
    private  Category foodCategory;
    * */

  @Column(length = 100)
  @ElementCollection
  private List<String> images;

  private boolean available;

  @ManyToOne
  private Restaurant restaurant;

  private boolean isVegetarian;
  private boolean isSeasonal;

  @ManyToMany/* One food can have multiple ingredients*/
  private List<IngredientsItem> ingredients = new ArrayList<>();

  private Date creationDate;
}
