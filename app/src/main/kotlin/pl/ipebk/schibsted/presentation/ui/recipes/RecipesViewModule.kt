package pl.ipebk.schibsted.presentation.ui.recipes

import dagger.Module
import dagger.Provides
import pl.ipebk.schibsted.presentation.ui.di.ActivityModule

@Module
class RecipesViewModule(activity: RecipesActivity) : ActivityModule(activity) {
  @Provides
  fun providePresented(impl: RecipesPresenter): RecipesContract.Presenter = impl
}