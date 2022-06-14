package es.marcrdz.presentation.base

sealed class ViewState<out T: State> {
    object Idle: ViewState<Nothing>()
    object Loading: ViewState<Nothing>()
    class Fail(val error: ErrorState): ViewState<Nothing>()
    class StateChange<out T: State>(val state: T): ViewState<T>()
}

open class State

sealed class ErrorState: State() {
    object Unknown: ErrorState()
    object NoData: ErrorState()
    object Network : ErrorState()
    object Server : ErrorState()
    class Exception(type: String, msg: String): ErrorState()
}