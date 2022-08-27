package es.marcrdz.ui.base

import es.marcrdz.presentation.base.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseStateHolder<in S : Report> {
    protected val _backgroundState: MutableSharedFlow<BackgroundReport> by lazy { MutableSharedFlow() }
    val backgroundState: SharedFlow<BackgroundReport>
        get() = _backgroundState.asSharedFlow()
    protected val _failState: MutableSharedFlow<FailState> by lazy { MutableSharedFlow() }
    val failState: SharedFlow<FailState>
        get() = _failState.asSharedFlow()

    protected abstract suspend fun emitStateChange(state: StateChange<S>)

    suspend fun emit(state: ViewState<S>) {
        when(state) {
            is BackgroundState -> _backgroundState.emit(state.report)
            is FailState -> _failState.emit(state)
            is StateChange -> emitStateChange(state)
        }
    }

}
