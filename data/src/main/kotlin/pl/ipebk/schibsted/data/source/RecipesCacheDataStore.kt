package pl.ipebk.schibsted.data.source

import io.reactivex.Completable
import io.reactivex.Single
import pl.ipebk.schibsted.data.model.RecipeEntity
import pl.ipebk.schibsted.data.repository.RecipesCache
import pl.ipebk.schibsted.data.repository.RecipesDataStore
import javax.inject.Inject

/**
 * Implementation of the [RecipesDataStore] interface to provide a means of communicating
 * with the local data source
 */
class RecipesCacheDataStore @Inject constructor(
  private val recipesCache: RecipesCache) : RecipesDataStore {

  override fun saveRecipes(recipes: List<RecipeEntity>): Completable {
    return recipesCache.saveRecipes(recipes)
  }

  override fun getAllRecipes(): Single<List<RecipeEntity>> {
    return recipesCache.getAllRecipes()
  }

  override fun searchForRecipe(phrase: String): Single<List<RecipeEntity>> {
    return recipesCache.searchForRecipe(phrase)
  }

  override fun getRecipe(id: Long): Single<RecipeEntity> {
    return recipesCache.getRecipe(id)
  }
}