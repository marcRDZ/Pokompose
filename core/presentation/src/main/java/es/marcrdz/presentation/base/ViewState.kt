package es.marcrdz.presentation.base

sealed class ViewState<out T : Event>

sealed class BackgroundState : ViewState<Nothing>() {
    object Idle : BackgroundState()
    object Loading : BackgroundState()
}

sealed class FailState : ViewState<Nothing>() {
    object Unknown : FailState()
    object NoData : FailState()
    object Network : FailState()
    object Server : FailState()
    class Exception(val type: String, val msg: String) : FailState()
}

class StateChange<out T : Event>(val event: T) : ViewState<T>()
