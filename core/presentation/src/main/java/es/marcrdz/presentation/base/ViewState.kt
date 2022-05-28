package es.marcrdz.presentation.base

import es.marcrdz.domain.domain.DomainError

sealed class ViewState<out T: State> {
    object Loading: ViewState<Nothing>()
    object Idle: ViewState<Nothing>()
    class StateChange<out T: State>(val state: T): ViewState<T>() { }
    class Fail<out T: DomainError>(val error: T): ViewState<Nothing>() { }
}

open class State