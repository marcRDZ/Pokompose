package es.marcrdz.ui.base

import es.marcrdz.presentation.base.Event

interface BaseView<E : Event, D : Event, VM:  BaseViewModel<E, D>> {

    val viewModel: VM

}
