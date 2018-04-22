package pl.ipebk.schibsted.presentation

import android.app.Application
import dagger.Lazy
import pl.ipebk.schibsted.BuildConfig
import pl.ipebk.schibsted.presentation.appDi.ApplicationComponent
import pl.ipebk.schibsted.presentation.appDi.ApplicationModule
import pl.ipebk.schibsted.presentation.appDi.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject

class App : Application() {
  @Inject
  lateinit var timberTree: Lazy<Timber.Tree>

  companion object {
    lateinit var graph: ApplicationComponent
  }

  override fun onCreate() {
    super.onCreate()

    initDependencyGraph()

    if (BuildConfig.DEBUG) {
      Timber.plant(timberTree.get())
    }
  }

  private fun initDependencyGraph() {
    graph = DaggerApplicationComponent.builder()
      .applicationModule(ApplicationModule(this))
      .build()
    graph.injectTo(this)
  }
}