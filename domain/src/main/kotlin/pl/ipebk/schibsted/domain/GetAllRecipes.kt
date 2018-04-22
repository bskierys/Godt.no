package pl.ipebk.schibsted.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import pl.ipebk.schibsted.domain.interactor.SingleUseCase
import pl.ipebk.schibsted.domain.model.Recipe
import pl.ipebk.schibsted.domain.repository.RecipeRepository

/**
 * Use case used for retrieving all 50 recipes from Godt.no
 */
class GetAllRecipes(private val repository: RecipeRepository,
                    backgroundScheduler: Scheduler,
                    foregroundScheduler: Scheduler) :
  SingleUseCase<List<Recipe>, Void?>(backgroundScheduler, foregroundScheduler) {

  override fun buildUseCaseObservable(params: Void?): Single<List<Recipe>> {
    return repository.getAllRecipes()
  }
}