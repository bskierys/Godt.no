package pl.ipebk.schibsted.cache.mapper

import io.realm.Realm
import io.realm.RealmList
import pl.ipebk.schibsted.cache.model.*
import pl.ipebk.schibsted.data.model.*
import javax.inject.Inject

class RecipeEntityMapper @Inject constructor() {
  /**
   * Maps to realm object. Important: Use only within transaction
   */
  fun mapToRealm(entity: RecipeEntity, realm: Realm) : RealmRecipe {
    val ingredients = RealmList<RealmIngredient>()
    val steps = RealmList<RealmStep>()
    val images = RealmList<RealmImage>()

    entity.ingredients.forEach { ingredients.add(mapToRealm(it, realm)) }
    entity.steps.forEach { steps.add(mapToRealm(it, realm)) }
    entity.images.forEach { images.add(mapToRealm(it, realm)) }

    val recipe = RealmRecipe()
    recipe.id = entity.id
    recipe.title = entity.title
    recipe.description = entity.description
    recipe.basicPortionNumber = entity.basicPortionNumber
    recipe.preparationTime = entity.preparationTime
    recipe.publishedAt = entity.publishedAt
    recipe.updatedAt = entity.updatedAt
    recipe.ingredients = ingredients
    recipe.steps = steps
    recipe.images = images

    realm.insertOrUpdate(recipe)

    return recipe
  }

  private fun mapToRealm(entity: IngredientEntity, realm: Realm) : RealmIngredient {
    val list = RealmList<RealmIngredientElement>()
    entity.elements.forEach { list.add(mapToRealm(it, realm)) }

    val ingredient = RealmIngredient()
    ingredient.id = entity.id
    ingredient.name = entity.name
    ingredient.elements = list
    realm.insertOrUpdate(ingredient)
    return ingredient
  }

  private fun mapToRealm(entity: IngredientElementEntity, realm: Realm) : RealmIngredientElement {
    val ingredientElement = RealmIngredientElement()
    ingredientElement.id = entity.id
    ingredientElement.amount = entity.amount
    ingredientElement.hint = entity.hint
    ingredientElement.name = entity.name
    ingredientElement.unitName = entity.unitName
    ingredientElement.symbol = entity.symbol
    realm.insertOrUpdate(ingredientElement)
    return ingredientElement
  }

  private fun mapToRealm(entity: StepEntity, realm: Realm) : RealmStep {
    val step = RealmStep()
    step.id = entity.id
    step.heading = entity.heading
    step.description = entity.description
    realm.insertOrUpdate(step)
    return step
  }

  private fun mapToRealm(entity: ImageEntity, realm: Realm) : RealmImage {
    val image = RealmImage()
    image.imboId = entity.imboId
    image.url = entity.url
    realm.insertOrUpdate(image)
    return image
  }

  fun mapFromRealm(model: RealmRecipe) : RecipeEntity {
    val ingredients = model.ingredients.map { mapFromRealm(it) }
    val steps = model.steps.map { mapFromRealm(it) }
    val images = model.images.map { mapFromRealm(it) }

    return RecipeEntity(model.title,
      model.description,
      model.basicPortionNumber,
      model.preparationTime,
      model.id!!,
      model.publishedAt,
      model.updatedAt,
      ingredients,
      steps,
      images)
  }

  private fun mapFromRealm(model: RealmIngredient) : IngredientEntity {
    val list = model.elements.map { mapFromRealm(it) }
    return IngredientEntity(model.id, model.name, list)
  }

  private fun mapFromRealm(model: RealmIngredientElement): IngredientElementEntity {
    return IngredientElementEntity(model.id!!, model.amount, model.hint,
      model.name, model.unitName, model.symbol)
  }

  private fun mapFromRealm(model: RealmStep) : StepEntity {
    return StepEntity(model.id!!, model.heading, model.description)
  }

  fun mapFromRealm(model: RealmImage) : ImageEntity {
    return ImageEntity(model.imboId, model.url)
  }
}