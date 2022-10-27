package es.marcrdz.presentation

import es.marcrdz.presentation.base.Event
import es.marcrdz.presentation.base.LifecycleEvent
import es.marcrdz.presentation.base.UserEvent
import es.marcrdz.presentation.base.ViewState
import kotlinx.coroutines.flow.Flow

interface PresentationContract {

    interface EventFlowHandler<in I : Event, out O : Event> {

        suspend fun handleInit(): Flow<ViewState<O>>

        suspend fun handleClear(): Flow<ViewState<O>>

        suspend fun handleLifecycle(viewEvent: LifecycleEvent): Flow<ViewState<O>>

        suspend fun handleEvent(viewEvent: UserEvent<I>): Flow<ViewState<O>>

    }

}
