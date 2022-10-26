package es.marcrdz.ui.base

import es.marcrdz.presentation.base.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseStateHolder<in T : Event> {
    private val _backgroundState: MutableSharedFlow<BackgroundState> by lazy { MutableSharedFlow() }
    val backgroundState: SharedFlow<BackgroundState>
        get() = _backgroundState.asSharedFlow()
    private val _failState: MutableSharedFlow<FailState> by lazy { MutableSharedFlow() }
    val failState: SharedFlow<FailState>
        get() = _failState.asSharedFlow()

    protected abstract suspend fun emitStateChange(event: T)

    suspend fun emitViewState(state: ViewState<T>) {
        when(state) {
            is BackgroundState -> _backgroundState.emit(state)
            is FailState -> _failState.emit(state)
            is StateChange -> emitStateChange(state.event)
        }
    }

}
