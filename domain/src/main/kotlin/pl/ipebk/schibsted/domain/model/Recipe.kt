package pl.ipebk.schibsted.domain.model

import java.util.*

data class Recipe(val title: String,
                  val description: String,
                  val basicPortionNumber: Int,
                  val preparationTime: Int,
                  val id: Long,
                  val publishedAt: Date?,
                  val updatedAt: Date?,
                  val ingredients: List<Ingredient>,
                  val steps: List<Step>,
                  val images: List<String?>)

data class Ingredient(val id: Long?, val name: String?,
                      val elements: List<IngredientElement>)

data class IngredientElement(val id: Long, val amount: Float, val hint: String?,
                             val name: String, val unitName: String?, val symbol: String?)

data class Step(val id: Long, val heading: String?, val description: String)