package pl.ipebk.schibsted.domain.interactor

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver

/**
 * Abstract class for a UseCase that returns an instance of a [Single].
 */
abstract class SingleUseCase<T, in Params> constructor(
  private val backgroundScheduler: Scheduler,
  private val foregroundScheduler: Scheduler) {

  private val disposables = CompositeDisposable()

  /**
   * Builds a [Single] which will be used when the current [SingleUseCase] is executed.
   */
  protected abstract fun buildUseCaseObservable(params: Params? = null): Single<T>

  /**
   * Executes the current use case.
   */
  open fun execute(singleObserver: DisposableSingleObserver<T>, params: Params? = null) {
    val single = this.buildUseCaseObservable(params)
      .subscribeOn(backgroundScheduler)
      .doOnError { }
      .observeOn(foregroundScheduler) as Single<T>
    addDisposable(single.subscribeWith(singleObserver))
  }

  /**
   * Dispose from current [CompositeDisposable].
   */
  fun dispose() {
    if (!disposables.isDisposed) disposables.dispose()
  }

  /**
   * Dispose from current [CompositeDisposable].
   */
  private fun addDisposable(disposable: Disposable) {
    disposables.add(disposable)
  }
}