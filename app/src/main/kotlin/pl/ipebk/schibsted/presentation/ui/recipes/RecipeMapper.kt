package pl.ipebk.schibsted.presentation.ui.recipes

import pl.ipebk.schibsted.domain.model.Recipe
import javax.inject.Inject

/**
 * Maps domain models into view ones
 */
class RecipeMapper @Inject constructor() {
  fun mapFromRecipe(recipe: Recipe): RecipeListModel {
    val ingredientsBuilder = StringBuilder()
    recipe.ingredients.forEach { ingredient ->
      ingredient.elements.forEach {
        ingredientsBuilder.append(it.name)
        ingredientsBuilder.append(", ")
      }
    }
    // TODO: this isn't valid method to handle building text, and should be fixed
    val ingredients = ingredientsBuilder.toString()
    val ingredientsTrimmed = ingredients.substring(0, ingredients.length - 2)

    return RecipeListModel(recipe.title, recipe.description,
      recipe.images[0], ingredientsTrimmed)
  }
}