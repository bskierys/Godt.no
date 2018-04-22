package pl.ipebk.schibsted.data.repository

import io.reactivex.Single
import pl.ipebk.schibsted.data.model.RecipeEntity

/**
 * Interface defining methods for the caching of Recipes. This is to be implemented by the
 * remote layer, using this interface as a way of communicating.
 */
interface RecipesRemote {

  /**
   * Retrieve list of recipes from remote server
   */
  fun getRecipes(): Single<List<RecipeEntity>>

  /**
   * @return if remote is currently available
   */
  fun isAvailable(): Boolean
}