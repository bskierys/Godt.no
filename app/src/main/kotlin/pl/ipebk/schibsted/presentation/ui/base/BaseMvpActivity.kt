package pl.ipebk.schibsted.presentation.ui.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import pl.ipebk.schibsted.presentation.App
import pl.ipebk.schibsted.presentation.appDi.ApplicationComponent
import javax.inject.Inject

abstract class BaseMvpActivity<in V : BaseMvpView, T : BaseMvpPresenter<V>>
  : AppCompatActivity(), BaseMvpView {

  @Inject
  protected lateinit var presenter: T

  @CallSuper
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    injectDependencies(App.graph)
    presenter.attachView(this as V)
  }

  @CallSuper
  override fun onDestroy() {
    super.onDestroy()
    presenter.detachView()
  }

  abstract fun injectDependencies(graph: ApplicationComponent)
}