package pl.ipebk.schibsted.presentation.ui.base

interface BaseMvpPresenter<in V : BaseMvpView> {

  fun attachView(view: V)

  fun detachView()
}