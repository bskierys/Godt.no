package pl.ipebk.schibsted.domain.repository

import io.reactivex.Single
import pl.ipebk.schibsted.domain.model.Recipe

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface RecipeRepository {
  /**
   * @return 50 first recipes from remote or cached
   */
  fun getAllRecipes(): Single<List<Recipe>>

  /**
   * Searches for recipes. It searches by title and by ingredient
   * @param phrase beginning of title or ingredient name
   */
  fun searchForRecipe(phrase: String): Single<List<Recipe>>

  /**
   * @return [Recipe] for id
   */
  fun getRecipe(id: Long): Single<Recipe>
}