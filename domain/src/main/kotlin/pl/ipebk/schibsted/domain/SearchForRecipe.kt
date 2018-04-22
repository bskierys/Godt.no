package pl.ipebk.schibsted.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import pl.ipebk.schibsted.domain.interactor.SingleUseCase
import pl.ipebk.schibsted.domain.model.Recipe
import pl.ipebk.schibsted.domain.repository.RecipeRepository

/**
 * Use case used for searching for recipes. It searches by title and by ingredient
 */
class SearchForRecipe(private val repository: RecipeRepository,
                      backgroundScheduler: Scheduler,
                      foregroundScheduler: Scheduler) :
  SingleUseCase<List<Recipe>, String>(backgroundScheduler, foregroundScheduler) {

  override fun buildUseCaseObservable(params: String?): Single<List<Recipe>> {
    if(params == null) {
      return Single.just(arrayListOf())
    }

    return repository.searchForRecipe(params)
  }
}