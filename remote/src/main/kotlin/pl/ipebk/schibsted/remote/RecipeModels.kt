package pl.ipebk.schibsted.remote

import java.util.*


data class RecipeModel(val title: String,
                       val description: String,
                       val basicPortionNumber: Int,
                       val preparationTime: Int,
                       val id: Long,
                       val publishedAt: Date?,
                       val updatedAt: Date?,
                       val ingredients: List<IngredientModel>,
                       val steps: List<StepModel>,
                       val images: List<ImageModel>)

data class IngredientModel(val id: Long?, val name: String?,
                           val elements: List<IngredientElementModel>)

data class IngredientElementModel(val id: Long, val amount: Float, val hint: String?,
                                  val name: String, val unitName: String?, val symbol: String?)

data class StepModel(val id: Long, val heading: String?, val description: String)

data class ImageModel(val imboId: String?, val url: String?)