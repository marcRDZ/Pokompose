package es.marcrdz.presentation.base

sealed class ViewEvent<out T: Event> {
    class UserAction<out T : Event>(val event: T) : ViewEvent<T>()
    sealed class Lifecycle: ViewEvent<Nothing>() {
        object OnCreate: Lifecycle()
        object OnCreateView: Lifecycle()
        object OnViewCreated: Lifecycle()
        object OnStart: Lifecycle()
        object OnResume: Lifecycle()
        object OnPause: Lifecycle()
        object OnStop: Lifecycle()
        object OnDestroy: Lifecycle()
    }
}

open class Event

sealed class Direction: Event() {
    object Left: Direction()
    object Right: Direction()
}