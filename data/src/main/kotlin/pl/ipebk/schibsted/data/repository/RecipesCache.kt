package pl.ipebk.schibsted.data.repository

/**
 * Interface defining methods for the caching of Recipes. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface RecipesCache : RecipesDataStore {
  /**
   * Checks if data is already cached
   */
  fun isCached(): Boolean
}