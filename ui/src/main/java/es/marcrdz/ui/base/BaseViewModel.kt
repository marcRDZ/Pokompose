package es.marcrdz.ui.base

import androidx.lifecycle.ViewModel
import es.marcrdz.presentation.PresentationContract
import es.marcrdz.presentation.base.Event

abstract class BaseViewModel<E : Event, D : Event>(
    protected val handler: PresentationContract.EventFlowHandler<E, D>
) : ViewModel() {

    abstract val stateHolder: BaseStateHolder<E, D>

}
