package pl.ipebk.schibsted.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import pl.ipebk.schibsted.data.model.RecipeEntity

/**
 * Interface defining methods for the data operations related to Recipes.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface RecipesDataStore {

  fun getAllRecipes(): Single<List<RecipeEntity>>

  fun searchForRecipe(phrase: String): Single<List<RecipeEntity>>

  fun getRecipe(id: Long): Single<RecipeEntity>

  fun saveRecipes(recipes: List<RecipeEntity>): Completable

}