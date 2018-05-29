package kot.notes.notes.model.usecases

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

/**
 * Created by dzhafar on 23.05.18.
 */
abstract class UseCase<T> {

    private var compositeDisposable: CompositeDisposable
    private  var executorThread: Scheduler
    private var uiThread: Scheduler

    constructor(executorThread: Scheduler, uiThread: Scheduler) {
        this.executorThread = executorThread
        this.uiThread = uiThread;
        compositeDisposable = CompositeDisposable()
    }

    fun execute(disposableObserver: DisposableObserver<T>?) {

        if (disposableObserver == null) {
            throw IllegalArgumentException("disposableObserver must not be null")
        }

        val observable = this.createObservableUseCase().subscribeOn(executorThread).observeOn(uiThread)

        val observer = observable.subscribeWith(disposableObserver)
        compositeDisposable.add(observer)
    }


    fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    protected abstract fun createObservableUseCase(): Observable<T>
}