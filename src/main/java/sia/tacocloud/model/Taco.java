package sia.tacocloud.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.oss.driver.api.core.uuid.Uuids;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table("tacos") // Persists to the "tacos" table
public class Taco {

  @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED) // Defines the partition key
  private UUID id = Uuids.timeBased();

  @NotNull
  @Size(min = 5, message = "Name must be at least 5 characters long.")
  private String name;

  @NotNull
  @Size(min = 1, message = "You must choose at least 1 ingredient.")
  @Column("ingredients") // Maps the list to the "ingredients" column
  private List<IngredientUDT> ingredients = new ArrayList<>();

  @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING) // Defines the clustering key
  private Date createdAt = new Date();

  // public void addIngredient(Ingredient ingredient) {
  // this.ingredients.add(TacoUDRUtils.toIngredientUDT(ingredient));
  // }
  public void addIngredient(IngredientUDT ingredient) {
    this.ingredients.add(ingredient);
  }
}