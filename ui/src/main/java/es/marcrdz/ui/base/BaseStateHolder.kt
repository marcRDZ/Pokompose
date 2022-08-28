package es.marcrdz.ui.base

import es.marcrdz.presentation.base.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseStateHolder<in T : Report> {
    protected val _backgroundState: MutableSharedFlow<BackgroundReport> by lazy { MutableSharedFlow() }
    val backgroundState: SharedFlow<BackgroundReport>
        get() = _backgroundState.asSharedFlow()
    protected val _failState: MutableSharedFlow<ErrorReport> by lazy { MutableSharedFlow() }
    val failState: SharedFlow<ErrorReport>
        get() = _failState.asSharedFlow()

    protected abstract suspend fun emitStateChangeReport(report: T)

    suspend fun emitViewState(state: ViewState<T>) {
        when(state) {
            is BackgroundState -> _backgroundState.emit(state.report)
            is FailState -> _failState.emit(state.report)
            is StateChange -> emitStateChangeReport(state.report)
        }
    }

}
