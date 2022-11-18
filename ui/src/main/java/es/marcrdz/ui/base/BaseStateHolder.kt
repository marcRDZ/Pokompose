package es.marcrdz.ui.base

import es.marcrdz.presentation.base.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class BaseStateHolder<E : Event, D : Event>(
    private val scope: CoroutineScope
) {
    private val _uiEvents: MutableSharedFlow<UIEvent<E>> by lazy { MutableSharedFlow() }
    val uiEvents: SharedFlow<UIEvent<E>>
        get() = _uiEvents.asSharedFlow()
    private val _backgroundState: MutableSharedFlow<BackgroundState> by lazy { MutableSharedFlow() }
    val backgroundState: SharedFlow<BackgroundState>
        get() = _backgroundState.asSharedFlow()
    private val _failState: MutableSharedFlow<FailState> by lazy { MutableSharedFlow() }
    val failState: SharedFlow<FailState>
        get() = _failState.asSharedFlow()
    private val _viewState: MutableSharedFlow<ViewState<D>> by lazy { MutableSharedFlow() }
    val viewState: SharedFlow<ViewState<D>>
        get() = _viewState.asSharedFlow()
    private val _navState: MutableSharedFlow<NavState<D>> by lazy { MutableSharedFlow() }
    val navState: SharedFlow<NavState<D>>
        get() = _navState.asSharedFlow()

    fun emitViewState(state: UIState<D>) = scope.launch {
        when(state) {
            is BackgroundState -> _backgroundState.emit(state)
            is FailState -> _failState.emit(state)
            is ViewState -> _viewState.emit(state)
            is NavState -> _navState.emit(state)
        }
    }

    fun emitViewEvent(event: UIEvent<E>) = scope.launch {
        _uiEvents.emit(event)
    }

}
