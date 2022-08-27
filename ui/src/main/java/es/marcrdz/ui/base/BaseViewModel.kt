package es.marcrdz.ui.base

import androidx.lifecycle.ViewModel
import es.marcrdz.presentation.PresentationContract
import es.marcrdz.presentation.base.Event
import es.marcrdz.presentation.base.Report
import es.marcrdz.presentation.base.ViewEvent
import es.marcrdz.presentation.base.ViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel<E : Event, S : Report>(
    val handler: PresentationContract.EventHandler<ViewEvent<E>, ViewState<S>>
) : ViewModel() {

    internal abstract val stateHolder: BaseStateHolder<S>

    abstract fun processViewEvent(viewEvent: ViewEvent<E>)
}
