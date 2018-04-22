package pl.ipebk.schibsted.data

import io.reactivex.Completable
import io.reactivex.Single
import pl.ipebk.schibsted.data.mapper.RecipeMapper
import pl.ipebk.schibsted.data.model.RecipeEntity
import pl.ipebk.schibsted.data.repository.RecipesDataStore
import pl.ipebk.schibsted.data.source.RecipesDataStoreFactory
import pl.ipebk.schibsted.data.source.RecipesRemoteDataStore
import pl.ipebk.schibsted.domain.model.Recipe
import pl.ipebk.schibsted.domain.repository.RecipeRepository
import javax.inject.Inject

/**
 * Provides an implementation of the [RecipeRepository] interface for communicating to and from
 * data sources
 */
class RecipeDataRepository @Inject constructor(
  private val factory: RecipesDataStoreFactory,
  private val mapper: RecipeMapper) : RecipeRepository {

  override fun getAllRecipes(): Single<List<Recipe>> {
    val dataStore: RecipesDataStore

    try {
      dataStore = factory.retrieveDataStore()
    } catch (ex: Throwable) {
      return Single.error<List<Recipe>>(ex)
    }

    return dataStore.getAllRecipes()
      .flatMap { recipes ->
        if (dataStore is RecipesRemoteDataStore) {
          saveRecipeEntities(recipes).toSingle { recipes }
        } else {
          Single.just(recipes)
        }
      }
      .map { list ->
        list.map { mapper.mapFromEntity(it) }
      }
  }

  private fun saveRecipeEntities(recipes: List<RecipeEntity>): Completable {
    return factory.retrieveCacheDataStore().saveRecipes(recipes)
  }

  override fun searchForRecipe(phrase: String): Single<List<Recipe>> {
    return factory.retrieveCacheDataStore()
      .searchForRecipe(phrase)
      .map { list ->
        list.map { mapper.mapFromEntity(it) }
      }
  }

  override fun getRecipe(id: Long): Single<Recipe> {
    return factory.retrieveCacheDataStore()
      .getRecipe(id)
      .map { mapper.mapFromEntity(it) }
  }

}