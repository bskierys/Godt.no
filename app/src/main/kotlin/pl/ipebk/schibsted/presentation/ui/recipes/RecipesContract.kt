package pl.ipebk.schibsted.presentation.ui.recipes

import pl.ipebk.schibsted.domain.model.Recipe
import pl.ipebk.schibsted.presentation.ui.base.BaseMvpPresenter
import pl.ipebk.schibsted.presentation.ui.base.BaseMvpView

object RecipesContract {
  interface View : BaseMvpView {
    fun showRecipes(recipes: List<Recipe>, searchPhrase: String)
    fun hideRecipes()
    fun showLoading()
    fun hideLoading()
    fun showError()
    fun hideError()
  }

  interface Presenter : BaseMvpPresenter<View> {
    fun getAllRecipes()
    fun searchForPhrase(phrase: String)
  }
}