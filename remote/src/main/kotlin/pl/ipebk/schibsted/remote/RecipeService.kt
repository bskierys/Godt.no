package pl.ipebk.schibsted.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Defines the abstract methods used for interacting with the Godt.no API
 */
interface RecipeService {

  @GET("getRecipesListDetailed?tags=&size=thumbnail-medium&ratio=1&limit=50&from=0")
  fun getRecipes(@Query("tags") tags: String,
                 @Query("size") size: String,
                 @Query("ratio") ratio: Int,
                 @Query("limit") limit: Int,
                 @Query("from") from: Int) : Single<List<RecipeModel>>

  class RecipeResponse {
    lateinit var recipes: List<RecipeModel>
  }
}