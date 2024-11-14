package jeremu2907.cocktail;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @PostMapping(path = "/post")
    public @ResponseBody ResponseEntity<?> addRecipe(
        @RequestBody Recipe recipe
    )
    {
        try
        {
            Recipe r = new Recipe();
            r.setName(recipe.getName());
            r.setContent(recipe.getContent());
            recipeRepository.save(r);
            return ResponseEntity.ok(r);
        }
        catch(IllegalArgumentException e)
        {
            return ResponseEntity.badRequest().body(e.toString());
        }
        catch(NoSuchElementException e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(path="/patch")
    public @ResponseBody ResponseEntity<?> editRecipe(
        @RequestBody Recipe recipe
    )
    {
        try
        {
            Recipe r = recipeRepository.findById(recipe.getId()).get();
            r.setName(recipe.getName());
            r.setContent(recipe.getContent());
            recipeRepository.save(r);
            return ResponseEntity.ok(r);
        }
        catch(IllegalArgumentException e)
        {
            return ResponseEntity.badRequest().body(e.toString());
        }
        catch(NoSuchElementException e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody ResponseEntity<String> deleteRecipe(
        @RequestParam("id") Integer id
    )
    {
        try
        {
            recipeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        catch(IllegalArgumentException e)
        {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<Recipe>> getAllRecipe() {
        return ResponseEntity.ok(recipeRepository.findAll());
    }

    @GetMapping(path="/get")
    public @ResponseBody ResponseEntity<?> get(
        @RequestParam("id") Integer id
    )
    {
        try
        {
            Optional<Recipe> r = recipeRepository.findById(id);
            if(r.isPresent())
            {
                return ResponseEntity.ok(r.get());
            }
            else
            {
                return ResponseEntity.notFound().build();
            }
        }
        catch(IllegalArgumentException e)
        {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
}
