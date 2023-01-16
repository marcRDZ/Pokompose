package es.marcrdz.presentation.base

import es.marcrdz.presentation.domain.Destiny

sealed class UIState<out T : Event>

sealed class BackgroundState : UIState<Nothing>() {
    object Idle : BackgroundState()
    object Loading : BackgroundState()
}

sealed class FailState : UIState<Nothing>() {
    object Unknown : FailState()
    object NoData : FailState()
    object Network : FailState()
    object Server : FailState()
    class Exception(val type: String, val msg: String) : FailState()
}

class ViewState<out T : Event>(val event: T) : UIState<T>()

class NavState(val event: Destiny) : UIState<Nothing>()
