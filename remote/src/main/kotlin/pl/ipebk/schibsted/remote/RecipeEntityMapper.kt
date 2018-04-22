package pl.ipebk.schibsted.remote

import pl.ipebk.schibsted.data.model.*
import javax.inject.Inject

/**
 * Map a [RecipeEntity] to a [RecipeModel] instance when data is moving between
 * this later and the Data layer
 */
class RecipeEntityMapper @Inject constructor() {
  fun mapFromRemote(model: RecipeModel): RecipeEntity {
    val ingredients = model.ingredients.map { mapFromRemote(it) }
    val steps = model.steps.map { mapFromRemote(it) }
    val images = model.images.map { mapFromRemote(it) }

    return RecipeEntity(model.title,
      model.description,
      model.basicPortionNumber,
      model.preparationTime,
      model.id,
      model.publishedAt,
      model.updatedAt,
      ingredients,
      steps,
      images)
  }

  private fun mapFromRemote(model: IngredientElementModel): IngredientElementEntity {
    return IngredientElementEntity(model.id, model.amount, model.hint,
      model.name, model.unitName, model.symbol)
  }

  private fun mapFromRemote(model: IngredientModel) : IngredientEntity {
    val list = model.elements.map { mapFromRemote(it) }
    return IngredientEntity(model.id, model.name, list)
  }

  private fun mapFromRemote(model: StepModel) : StepEntity {
    return StepEntity(model.id, model.heading, model.description)
  }

  private fun mapFromRemote(model: ImageModel) : ImageEntity {
    return ImageEntity(model.imboId, model.url)
  }
}