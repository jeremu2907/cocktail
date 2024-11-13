package jeremu2907.cocktail;

import org.springframework.data.repository.CrudRepository;

import jeremu2907.cocktail.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    
}
