package jeremu2907.cocktail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/cocktail")
public class MainController {
    @Autowired
    private RecipeRepository recipeRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addRecipe(
        @RequestBody Recipe recipe
    )
    {
        Recipe r = new Recipe();
        r.setName(recipe.getName());
        r.setContent(recipe.getContent());
        recipeRepository.save(r);
        return "success";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Recipe> getAllRecipe() {
    return recipeRepository.findAll();
  }
}