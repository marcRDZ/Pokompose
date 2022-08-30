package es.marcrdz.presentation.base

sealed class ViewState<out T : Event>

class BackgroundState(val event: BackgroundEvent) : ViewState<Nothing>()
class FailState(val event: ErrorEvent) : ViewState<Nothing>()
class StateChange<out T : Event>(val event: T) : ViewState<T>()

sealed class BackgroundEvent : Event {
    object Idle : BackgroundEvent()
    object Loading : BackgroundEvent()
}

sealed class ErrorEvent : Event {
    object Unknown : ErrorEvent()
    object NoData : ErrorEvent()
    object Network : ErrorEvent()
    object Server : ErrorEvent()
    class Exception(type: String, msg: String) : ErrorEvent()
}