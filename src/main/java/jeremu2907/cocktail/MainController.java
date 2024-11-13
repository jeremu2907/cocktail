package jeremu2907.cocktail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/cocktail")
public class MainController {
    @Autowired
    private RecipeRepository recipeRepository;

    @PostMapping(path = "/post")
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

    @PatchMapping(path="/patch")
    public @ResponseBody String editRecipe(
        @RequestBody Recipe recipe
    )
    {
        Recipe r = recipeRepository.findById(recipe.getId()).get();
        r.setName(recipe.getName());
        r.setContent(recipe.getContent());
        recipeRepository.save(r);
        return "success";
    }
}
