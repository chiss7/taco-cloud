package sia.tacocloud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import sia.tacocloud.model.Ingredient;
import sia.tacocloud.model.Taco;
import sia.tacocloud.model.TacoOrder;
import sia.tacocloud.model.Ingredient.Type;
import sia.tacocloud.repository.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;

  // @Autowired
  public DesignTacoController(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    Iterable<Ingredient> ingredients = ingredientRepo.findAll();

    Type[] types = Ingredient.Type.values();
    for (Ingredient.Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(ingredients, type));
    }
  }

  @ModelAttribute(name = "tacoOrder")
  public TacoOrder order() {
    return new TacoOrder();
  }

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @GetMapping
  public String showDesignForm(Model model) {
    //model.asMap().forEach((name, value) -> log.info("Model attribute - Name: {}, Value: {}", name, value));
    return "design";
  }

  @PostMapping
  public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder){
    //  @Valid annotation tells Spring MVC to perform validation on the submitted Taco
    //  object after it’s bound to the submitted form data and before the processTaco()
    //  method is called.
    if (errors.hasErrors()) {
      log.info(errors.toString());
      return "design";
    }
    tacoOrder.addTaco(taco);
    log.info("Processing Taco {}", taco);
    return "redirect:/orders/current";
  }

  private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
    List<Ingredient> ingredientList = new ArrayList<>();
    ingredients.forEach(ingredientList::add);
    return ingredientList.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
  }

}
