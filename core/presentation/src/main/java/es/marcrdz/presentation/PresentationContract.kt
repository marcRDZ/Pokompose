package es.marcrdz.presentation

import es.marcrdz.presentation.base.*

interface PresentationContract {

    interface EventHandler<in I : ViewEvent<Event>, out O : ViewState<Report>> {

        suspend fun handleInit(viewState: suspend (O) -> Unit) {}

        suspend fun handleClear(viewState: suspend (O) -> Unit) {}

        suspend fun handleEvent(viewEvent: I, viewState: suspend (O) -> Unit) {}
    }
}
