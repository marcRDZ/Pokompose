package es.marcrdz.presentation.base

sealed class ViewEvent<out T : Event>

class UserEvent<out T :Event>(val event: T) : ViewEvent<T>()

sealed class LifecycleEvent : ViewEvent<Nothing>() {
    object OnCreate : LifecycleEvent()
    object OnCreateView : LifecycleEvent()
    object OnViewCreated : LifecycleEvent()
    object OnStart : LifecycleEvent()
    object OnResume : LifecycleEvent()
    object OnPause : LifecycleEvent()
    object OnStop : LifecycleEvent()
    object OnDestroy : LifecycleEvent()
}
