package pl.ipebk.schibsted.presentation.appDi

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import pl.ipebk.schibsted.BuildConfig
import pl.ipebk.schibsted.cache.RecipesCacheImpl
import pl.ipebk.schibsted.data.RecipeDataRepository
import pl.ipebk.schibsted.data.repository.RecipesCache
import pl.ipebk.schibsted.data.repository.RecipesRemote
import pl.ipebk.schibsted.domain.GetAllRecipes
import pl.ipebk.schibsted.domain.GetRecipe
import pl.ipebk.schibsted.domain.SearchForRecipe
import pl.ipebk.schibsted.domain.repository.RecipeRepository
import pl.ipebk.schibsted.presentation.data.network.NetworkInteractor
import pl.ipebk.schibsted.remote.ConnectionChecker
import pl.ipebk.schibsted.remote.RecipeService
import pl.ipebk.schibsted.remote.RecipeServiceFactory
import pl.ipebk.schibsted.remote.RecipesRemoteImpl
import javax.inject.Named

@Module
class DomainModule {
  @Provides
  internal fun provideRecipeRemote(impl: RecipesRemoteImpl): RecipesRemote = impl

  @Provides
  internal fun provideRecipeService(): RecipeService {
    return RecipeServiceFactory.makeRecipeService(BuildConfig.DEBUG)
  }

  @Provides
  fun provideGetAllRecipesCase(
    repository: RecipeRepository,
    @Named(Rx.POOL) backgroundScheduler: Scheduler,
    @Named(Rx.MAIN) foregroundScheduler: Scheduler):
    GetAllRecipes {

    return GetAllRecipes(repository, backgroundScheduler, foregroundScheduler)
  }

  @Provides
  fun provideGetRecipeCase(
    repository: RecipeRepository,
    @Named(Rx.POOL) backgroundScheduler: Scheduler,
    @Named(Rx.MAIN) foregroundScheduler: Scheduler):
    GetRecipe {

    return GetRecipe(repository, backgroundScheduler, foregroundScheduler)
  }

  @Provides
  fun provideSearchForRecipesCase(
    repository: RecipeRepository,
    @Named(Rx.POOL) backgroundScheduler: Scheduler,
    @Named(Rx.MAIN) foregroundScheduler: Scheduler):
    SearchForRecipe {

    return SearchForRecipe(repository, backgroundScheduler, foregroundScheduler)
  }

  @Provides
  fun provideRecipeRepository(impl: RecipeDataRepository): RecipeRepository = impl

  @Provides
  fun provideRecipesCache(impl: RecipesCacheImpl): RecipesCache = impl

  @Provides
  fun provideConnectionChecker(interactor: NetworkInteractor) : ConnectionChecker {
    return object: ConnectionChecker {
      override fun isInternetConnectionAvailable() = interactor.hasNetworkConnection()
    }
  }
}