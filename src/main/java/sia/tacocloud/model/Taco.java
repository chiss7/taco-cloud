package sia.tacocloud.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Taco {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Size(min = 5, message = "Name must be at least 5 characters long.")
  private String name;

  @NotNull
  @Size(min = 1, message = "You must choose at least 1 ingredient.")
  @ManyToMany()
  // @JoinTable(
  //   name = "Ingredient_Ref", // Nombre personalizado de la tabla intermedia
  //   joinColumns = @JoinColumn(name = "taco"), // Columna en Ingredient_Ref que referencia a Taco
  //   inverseJoinColumns = @JoinColumn(name = "ingredient") // Columna en Ingredient_Ref que referencia a Ingredient
  // )
  private List<Ingredient> ingredients = new ArrayList<>();

  private Date createdAt = new Date();

  public void addIngredient(Ingredient ingredient) {
    this.ingredients.add(ingredient);
  }
}