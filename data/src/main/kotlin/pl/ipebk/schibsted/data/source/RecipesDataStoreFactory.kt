package pl.ipebk.schibsted.data.source

import pl.ipebk.schibsted.data.repository.RecipesCache
import pl.ipebk.schibsted.data.repository.RecipesDataStore
import pl.ipebk.schibsted.data.repository.RecipesRemote
import java.io.IOException
import javax.inject.Inject

/**
 * Create an instance of a [RecipesDataStore]
 */
class RecipesDataStoreFactory @Inject constructor(
  private val recipesCache: RecipesCache,
  private val recipesCacheDataStore: RecipesCacheDataStore,
  private val recipesRemote: RecipesRemote,
  private val recipesRemoteDataStore: RecipesRemoteDataStore
) {

  /**
   * Returns a DataStore based on whether or not there is content in the cache and the cache
   * has not expired
   *
   * @throws IllegalStateException if no source is currently available
   */
  fun retrieveDataStore(): RecipesDataStore {
    if (recipesRemote.isAvailable()) {
      return retrieveRemoteDataStore()
    }

    if (recipesCache.isCached()) {
      return retrieveCacheDataStore()
    }

    throw IOException("No data source currently available")
  }

  /**
   * Return an instance of the Remote Data Store
   */
  fun retrieveCacheDataStore(): RecipesCacheDataStore {
    return recipesCacheDataStore
  }

  /**
   * Return an instance of the Cache Data Store
   */
  fun retrieveRemoteDataStore(): RecipesRemoteDataStore {
    return recipesRemoteDataStore
  }
}