package es.marcrdz.presentation

import es.marcrdz.presentation.base.Event
import es.marcrdz.presentation.base.State
import es.marcrdz.presentation.base.ViewEvent
import es.marcrdz.presentation.base.ViewState

interface PresentationContract {
    interface StateHandler<in I: ViewEvent<Event>, out O: ViewState<State>> {
        suspend fun handle(viewEvent: I, viewState: (O) -> Unit)
    }
}



