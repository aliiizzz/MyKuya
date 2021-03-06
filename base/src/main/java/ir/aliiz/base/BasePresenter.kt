package ir.aliiz.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BasePresenter<View> : ViewModel(), LifecycleObserver {

    private var view: View? = null
    private var viewLifecycle: Lifecycle? = null
    private val disposables = CompositeDisposable()

    fun attachView(view: View, viewLifecycle: Lifecycle) {
        this.view = view
        this.viewLifecycle = viewLifecycle

        viewLifecycle.addObserver(this)
        viewAttached()
    }

    abstract fun viewAttached()

    protected fun view(): View? {
        return view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onViewDestroyed() {
        view = null
        viewLifecycle = null
        disposables.clear()
    }

    protected fun Disposable.add() = this.also {
        disposables.add(it)
    }
}