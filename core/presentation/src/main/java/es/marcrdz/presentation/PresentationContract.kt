package es.marcrdz.presentation

import es.marcrdz.presentation.base.Event
import es.marcrdz.presentation.base.LifecycleEvent
import es.marcrdz.presentation.base.UserEvent
import es.marcrdz.presentation.base.UIState
import kotlinx.coroutines.flow.Flow

interface PresentationContract {

    interface EventFlowHandler<in I : Event, out O : Event> {

        suspend fun handleInit(): Flow<UIState<O>>

        suspend fun handleClear(): Flow<UIState<O>>

        suspend fun handleLifecycle(viewEvent: LifecycleEvent): Flow<UIState<O>>

        suspend fun handleEvent(viewEvent: UserEvent<I>): Flow<UIState<O>>

    }

}
