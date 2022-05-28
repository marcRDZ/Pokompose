package es.marcrdz.presentation.handlers.main

import es.marcrdz.presentation.base.Event

sealed class MainEvent: Event() {
    object Continue: MainEvent()
    class LogIn(val user: String, val pass: String): MainEvent()
}