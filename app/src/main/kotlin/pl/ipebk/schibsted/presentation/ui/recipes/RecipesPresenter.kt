package pl.ipebk.schibsted.presentation.ui.recipes

import io.reactivex.observers.DisposableSingleObserver
import pl.ipebk.schibsted.domain.GetAllRecipes
import pl.ipebk.schibsted.domain.SearchForRecipe
import pl.ipebk.schibsted.domain.model.Recipe
import pl.ipebk.schibsted.presentation.ui.base.BaseMvpPresenterImpl
import timber.log.Timber
import javax.inject.Inject

class RecipesPresenter @Inject constructor(
  private val getRecipes: GetAllRecipes,
  private val searchForRecipe: SearchForRecipe) :
  BaseMvpPresenterImpl<RecipesContract.View>(),
  RecipesContract.Presenter {

  private var lastSearchPhrase : String = ""

  override fun searchForPhrase(phrase: String) {
    lastSearchPhrase = phrase
    mvpView!!.hideRecipes()
    searchForRecipe.execute(ShowRecipesSubscriber(), phrase)
  }

  override fun getAllRecipes() {
    lastSearchPhrase = ""
    mvpView!!.hideError()
    mvpView!!.hideRecipes()
    mvpView!!.showLoading()
    getRecipes.execute(ShowRecipesSubscriber())
  }

  private inner class ShowRecipesSubscriber : DisposableSingleObserver<List<Recipe>>() {
    override fun onSuccess(t: List<Recipe>) {
      mvpView!!.showRecipes(t, lastSearchPhrase)
      mvpView!!.hideLoading()
      mvpView!!.hideError()
    }

    override fun onError(e: Throwable) {
      mvpView!!.hideLoading()
      mvpView!!.hideRecipes()
      mvpView!!.showError()
      Timber.e(e)
    }
  }
}