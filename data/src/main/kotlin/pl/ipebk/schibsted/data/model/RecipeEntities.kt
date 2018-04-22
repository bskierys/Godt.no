package pl.ipebk.schibsted.data.model

import java.util.*

data class RecipeEntity(val title: String,
                        val description: String,
                        val basicPortionNumber: Int,
                        val preparationTime: Int,
                        val id: Long,
                        val publishedAt: Date?,
                        val updatedAt: Date?,
                        val ingredients: List<IngredientEntity>,
                        val steps: List<StepEntity>,
                        val images: List<ImageEntity>)

data class IngredientEntity(val id: Long?, val name: String?,
                            val elements: List<IngredientElementEntity>)

data class IngredientElementEntity(val id: Long, val amount: Float, val hint: String?,
                                   val name: String, val unitName: String?, val symbol: String?)

data class StepEntity(val id: Long, val heading: String?, val description: String)

data class ImageEntity(val imboId: String?, val url: String?)