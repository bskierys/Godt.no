package pl.ipebk.schibsted.remote

import io.reactivex.Single
import pl.ipebk.schibsted.data.model.RecipeEntity
import pl.ipebk.schibsted.data.repository.RecipesRemote
import javax.inject.Inject

/**
 * Remote implementation for retrieving Recipe instances. This class implements the
 * [RecipesRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class RecipesRemoteImpl @Inject constructor(
  private val recipeService: RecipeService,
  private val recipeMapper: RecipeEntityMapper,
  private val connectionChecker: ConnectionChecker) : RecipesRemote {

  override fun isAvailable(): Boolean {
    return connectionChecker.isInternetConnectionAvailable()
  }

  override fun getRecipes(): Single<List<RecipeEntity>> {
    return recipeService
      .getRecipes("", "thumbnail-medium", 1, 50, 0)
      .map {
        it.map { model -> recipeMapper.mapFromRemote(model) }
      }
  }
}