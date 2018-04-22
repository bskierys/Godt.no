package pl.ipebk.schibsted.data.mapper

import pl.ipebk.schibsted.data.model.*
import pl.ipebk.schibsted.domain.model.Ingredient
import pl.ipebk.schibsted.domain.model.IngredientElement
import pl.ipebk.schibsted.domain.model.Recipe
import pl.ipebk.schibsted.domain.model.Step
import javax.inject.Inject

/**
 * Map a [RecipeEntity] to and from a [Recipe] instance when data is moving between
 * this later and the Domain layer
 */
class RecipeMapper @Inject constructor() {
  fun mapFromEntity(entity: RecipeEntity) : Recipe {
    val ingredients = entity.ingredients.map { mapFromEntity(it) }
    val steps = entity.steps.map { mapFromEntity(it) }
    val images = entity.images.map { mapFromEntity(it) }

    return Recipe(entity.title,
      entity.description,
      entity.basicPortionNumber,
      entity.preparationTime,
      entity.id,
      entity.publishedAt,
      entity.updatedAt,
      ingredients,
      steps,
      images)
  }

  private fun mapFromEntity(entity: IngredientElementEntity): IngredientElement {
    return IngredientElement(entity.id, entity.amount, entity.hint,
      entity.name, entity.unitName, entity.symbol)
  }

  private fun mapFromEntity(entity: IngredientEntity) : Ingredient {
    val list = entity.elements.map { mapFromEntity(it) }
    return Ingredient(entity.id, entity.name, list)
  }

  private fun mapFromEntity(entity: StepEntity) : Step {
    return Step(entity.id, entity.heading, entity.description)
  }

  private fun mapFromEntity(entity: ImageEntity) : String? {
    return entity.url
  }
}