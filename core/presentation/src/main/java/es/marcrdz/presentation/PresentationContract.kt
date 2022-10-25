package es.marcrdz.presentation

import es.marcrdz.presentation.base.*
import kotlinx.coroutines.flow.Flow

interface PresentationContract {

    interface EventHandler<in I : ViewEvent<Event>, out O : ViewState<Event>> {

        suspend fun handleInit(viewState: suspend (O) -> Unit) {}

        suspend fun handleClear(viewState: suspend (O) -> Unit) {}

        suspend fun handleEvent(viewEvent: I, viewState: suspend (O) -> Unit) {}
    }

    interface EventFlowHandler<in I : UserEvent<Event>, out O : ViewState<Event>> {

        suspend fun handleInit(): Flow<O>

        suspend fun handleClear(): Flow<O>

        suspend fun handleEvent(viewEvent: I): Flow<O>

    }

}
