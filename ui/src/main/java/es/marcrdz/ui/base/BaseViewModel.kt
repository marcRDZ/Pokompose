package es.marcrdz.ui.base

import androidx.lifecycle.ViewModel
import es.marcrdz.presentation.PresentationContract
import es.marcrdz.presentation.base.Event
import es.marcrdz.presentation.base.State
import es.marcrdz.presentation.base.ViewEvent
import es.marcrdz.presentation.base.ViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel<E : Event, S : State>(
    val handler: PresentationContract.StateHandler<ViewEvent<E>, ViewState<S>>
) : ViewModel() {

    protected val _viewState: MutableSharedFlow<ViewState<S>> by lazy { MutableSharedFlow() }
    val state: SharedFlow<ViewState<S>>
        get() = _viewState.asSharedFlow()

    abstract fun processViewEvent(viewEvent: ViewEvent<E>)
}
