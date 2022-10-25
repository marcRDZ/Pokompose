package es.marcrdz.ui.base

import androidx.compose.runtime.Composable
import es.marcrdz.presentation.base.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseStateHolder<in T : Event> {
    protected val _backgroundState: MutableSharedFlow<BackgroundEvent> by lazy { MutableSharedFlow() }
    val backgroundState: SharedFlow<BackgroundEvent>
        get() = _backgroundState.asSharedFlow()
    protected val _failState: MutableSharedFlow<ErrorEvent> by lazy { MutableSharedFlow() }
    val failState: SharedFlow<ErrorEvent>
        get() = _failState.asSharedFlow()

    protected abstract suspend fun emitStateChange(event: T)

    suspend fun emitViewState(state: ViewState<T>) {
        when(state) {
            is BackgroundState -> _backgroundState.emit(state.event)
            is FailState -> _failState.emit(state.event)
            is StateChange -> emitStateChange(state.event)
        }
    }

}
