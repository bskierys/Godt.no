package pl.ipebk.schibsted.presentation.ui.recipes

import android.content.res.Resources
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import pl.ipebk.schibsted.R
import pl.ipebk.schibsted.presentation.ui.di.ActivityScope
import javax.inject.Inject

@ActivityScope
class RecipesAdapter @Inject constructor(
  private val inflater: LayoutInflater,
  private val picasso: Picasso,
  private val resources: Resources) :
  RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

  private val accentColor = resources.getColor(R.color.colorAccent)
  private val noAccentColor = resources.getColor(R.color.transparent)

  var recipes: List<RecipeListModel> = arrayListOf()
  var searchPhrase = ""

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
    val itemView = inflater.inflate(R.layout.item_recipe, parent, false)
    return RecipeViewHolder(itemView)
  }

  override fun getItemCount(): Int {
    return recipes.size
  }

  override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
    val recipe = recipes[position]
    holder.title.text = recipe.title
    holder.ingredients.text = resources.getString(R.string.ingredients_prefix, recipe.ingredients)
    holder.image.invalidate()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      holder.description.text = Html.fromHtml(recipe.description, Html.FROM_HTML_MODE_COMPACT)
    } else {
      holder.description.text = Html.fromHtml(recipe.description)
    }

    picasso.load(recipe.imageUrl).into(holder.image)

    if (searchPhrase.isEmpty() || searchPhrase == "" ) {
      holder.title.setBackgroundColor(noAccentColor)
      holder.ingredients.setBackgroundColor(noAccentColor)
      return
    }

    if (recipe.title.contains(searchPhrase)) {
      holder.title.setBackgroundColor(accentColor)
    } else {
      holder.title.setBackgroundColor(noAccentColor)
    }
    if (recipe.ingredients.contains(searchPhrase)) {
      holder.ingredients.setBackgroundColor(accentColor)
    } else {
      holder.ingredients.setBackgroundColor(noAccentColor)
    }
  }

  class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var image: ImageView
    var title: TextView
    var description: TextView
    var ingredients: TextView

    init {
      image = view.findViewById(R.id.img_recipe)
      title = view.findViewById(R.id.txt_recipe_title)
      description = view.findViewById(R.id.txt_recipe_description)
      ingredients = view.findViewById(R.id.txt_ingredients)
    }
  }
}