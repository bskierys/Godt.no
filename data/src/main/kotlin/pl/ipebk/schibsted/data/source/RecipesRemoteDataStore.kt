package pl.ipebk.schibsted.data.source

import io.reactivex.Completable
import io.reactivex.Single
import pl.ipebk.schibsted.data.model.RecipeEntity
import pl.ipebk.schibsted.data.repository.RecipesDataStore
import pl.ipebk.schibsted.data.repository.RecipesRemote
import javax.inject.Inject

/**
 * Implementation of the [RecipesRemoteDataStore] interface to provide a means of communicating
 * with the remote data source
 */
class RecipesRemoteDataStore @Inject constructor(
  private val recipesRemote: RecipesRemote) : RecipesDataStore {

  override fun getAllRecipes(): Single<List<RecipeEntity>> {
    return recipesRemote.getRecipes()
  }

  override fun saveRecipes(recipes: List<RecipeEntity>): Completable {
    throw UnsupportedOperationException()
  }

  override fun searchForRecipe(phrase: String): Single<List<RecipeEntity>> {
    throw UnsupportedOperationException()
  }

  override fun getRecipe(id: Long): Single<RecipeEntity> {
    throw UnsupportedOperationException()
  }
}