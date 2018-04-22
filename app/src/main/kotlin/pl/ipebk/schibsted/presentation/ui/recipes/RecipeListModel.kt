package pl.ipebk.schibsted.presentation.ui.recipes

/**
 * simplified data models to be presented in the list views
 */
data class RecipeListModel(val title: String, val description: String,
                           val imageUrl: String?, val ingredients: String)