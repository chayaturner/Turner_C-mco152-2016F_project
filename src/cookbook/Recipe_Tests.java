package cookbook;

import static org.junit.Assert.*;

import org.junit.Test;

public class Recipe_Tests {

	@Test
	public void createRecipeSuccessfully(){
		//Recipe: String name, String description, String[] ingredients, String[] instructions
		String name = "Salad";
		String description = "Bubby's favorite israeli salad";
		String[] ingredients = {"cucumbers", "tomatoes", "lettuce", "salt", "lemon juice", "oil"};
		String[] instructions = {"Cut vegetables", "Mix", "Let sit for an hour"};
		Recipe newRecipe = new Recipe(name, description, ingredients, instructions);
		assertEquals(newRecipe.getName(), name);
		assertEquals(newRecipe.getDescription(), description);
		assertArrayEquals(newRecipe.getIngredients(), ingredients);
		assertArrayEquals(newRecipe.getInstructions(), instructions);
	}
	
	@Test
	//for now, null is allowed and Recipe will still be created
	public void createRecipeWithNull(){
		String name = "Pasta";
		String description = null;
		String[] ingredients = {"pasta", "cheese", "tomatoe sauce"};
		String[] instructions = null;
		Recipe newRecipe = new Recipe(name, description, ingredients, instructions);
		assertNotNull(newRecipe);
		}
}
