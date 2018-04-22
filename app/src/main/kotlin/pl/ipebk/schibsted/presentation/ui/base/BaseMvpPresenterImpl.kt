package pl.ipebk.schibsted.presentation.ui.base

open class BaseMvpPresenterImpl<V : BaseMvpView> : BaseMvpPresenter<V> {

  protected var mvpView: V? = null

  override fun attachView(view: V) {
    mvpView = view
  }

  override fun detachView() {
    mvpView = null
  }
}