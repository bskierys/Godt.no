package pl.ipebk.schibsted.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import pl.ipebk.schibsted.domain.interactor.SingleUseCase
import pl.ipebk.schibsted.domain.model.Recipe
import pl.ipebk.schibsted.domain.repository.RecipeRepository

/**
 * Use case used for fetching single recipe by it's id
 */
class GetRecipe (private val repository: RecipeRepository,
                 backgroundScheduler: Scheduler,
                 foregroundScheduler: Scheduler) :
  SingleUseCase<Recipe, Long?>(backgroundScheduler, foregroundScheduler) {

  override fun buildUseCaseObservable(params: Long?): Single<Recipe> {
    return repository.getRecipe(params!!)
  }
}