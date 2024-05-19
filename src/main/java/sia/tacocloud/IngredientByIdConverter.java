package sia.tacocloud;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sia.tacocloud.model.Ingredient;
import sia.tacocloud.model.IngredientUDT;
import sia.tacocloud.repository.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, IngredientUDT> {

  private IngredientRepository ingredientRepo;

  // @Autowired
  public IngredientByIdConverter(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  @Override
  public IngredientUDT convert(String id) {
    Ingredient ingredient = ingredientRepo.findById(id).orElse(null);
    if (ingredient != null) {
      return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }
    return null;
  }
}
