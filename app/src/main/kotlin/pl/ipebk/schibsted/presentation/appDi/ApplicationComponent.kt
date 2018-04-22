package pl.ipebk.schibsted.presentation.appDi

import dagger.Component
import pl.ipebk.schibsted.presentation.App
import pl.ipebk.schibsted.presentation.data.network.NetworkModule
import pl.ipebk.schibsted.presentation.ui.recipes.RecipesViewComponent
import pl.ipebk.schibsted.presentation.ui.recipes.RecipesViewModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
  ApplicationModule::class,
  NetworkModule::class,
  DomainModule::class,
  Rx::class
])
interface ApplicationComponent {
  fun injectTo(app: App)

  fun plus(module: RecipesViewModule): RecipesViewComponent
}