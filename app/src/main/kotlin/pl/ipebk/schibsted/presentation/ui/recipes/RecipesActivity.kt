package pl.ipebk.schibsted.presentation.ui.recipes

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.activity_recipes.*
import pl.ipebk.schibsted.R
import pl.ipebk.schibsted.domain.model.Recipe
import pl.ipebk.schibsted.presentation.appDi.ApplicationComponent
import pl.ipebk.schibsted.presentation.ui.base.BaseMvpActivity
import timber.log.Timber
import javax.inject.Inject
import android.support.v7.widget.SearchView
import android.view.MenuItem

class RecipesActivity :
  BaseMvpActivity<RecipesContract.View, RecipesContract.Presenter>(),
  RecipesContract.View {

  @Inject lateinit var adapter: RecipesAdapter
  @Inject lateinit var mapper: RecipeMapper

  lateinit var searchMenu: MenuItem

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_recipes)
    setSupportActionBar(listToolbar)
    setupRecyclerView()
    setupSwipeToRefresh()
    presenter.getAllRecipes()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.search_menu, menu)

    searchMenu = menu!!.findItem(R.id.action_search)

    val mSearchView = searchMenu.actionView as SearchView
    mSearchView.queryHint = getString(R.string.search_hint)

    mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String): Boolean {
        presenter.searchForPhrase(query)
        searchMenu.collapseActionView()
        return true
      }

      override fun onQueryTextChange(newText: String): Boolean {
        presenter.searchForPhrase(newText)
        return true
      }
    })

    return super.onCreateOptionsMenu(menu)
  }

  override fun injectDependencies(graph: ApplicationComponent) {
    graph.plus(RecipesViewModule(this)).injectTo(this)
  }

  override fun showLoading() {
    loadingView.visibility = View.VISIBLE
    listSwipeRefresh.setEnabled(false)
  }

  override fun hideLoading() {
    loadingView.visibility = View.INVISIBLE
    listSwipeRefresh.isRefreshing = false
  }

  override fun showError() {
    errorView.visibility = View.VISIBLE
    listSwipeRefresh.setEnabled(false)
  }

  override fun hideError() {
    errorView.visibility = View.INVISIBLE
  }

  override fun showRecipes(recipes: List<Recipe>, searchPhrase: String) {
    Timber.d("Number of recipes: ${recipes.size}, with phrase: $searchPhrase")

    adapter.searchPhrase = searchPhrase
    adapter.recipes = recipes.map { mapper.mapFromRecipe(it) }
    adapter.notifyDataSetChanged()
    listSwipeRefresh.setEnabled(true)
    listRecyclerView.visibility = View.VISIBLE
  }

  override fun hideRecipes() {
    listRecyclerView.visibility = View.GONE
  }

  private fun setupRecyclerView() {
    listRecyclerView.layoutManager = LinearLayoutManager(this)
    listRecyclerView.adapter = adapter
  }

  private fun setupSwipeToRefresh() {
    listSwipeRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent))
    listSwipeRefresh.setOnRefreshListener {
      searchMenu.collapseActionView()
      presenter.getAllRecipes()
    }
  }
}
