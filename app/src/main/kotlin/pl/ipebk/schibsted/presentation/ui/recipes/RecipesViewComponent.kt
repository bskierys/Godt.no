package pl.ipebk.schibsted.presentation.ui.recipes

import dagger.Subcomponent
import pl.ipebk.schibsted.presentation.ui.di.ActivityScope

@ActivityScope
@Subcomponent(modules = [
  RecipesViewModule::class
])
interface RecipesViewComponent {
  fun injectTo(activity: RecipesActivity)
}