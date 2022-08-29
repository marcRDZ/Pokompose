package es.marcrdz.ui.base

import es.marcrdz.presentation.base.ErrorEvent
import es.marcrdz.presentation.base.Event

interface BaseView<E : Event, D : Event, SH: BaseStateHolder<D>, VM:  BaseViewModel<E, D, SH>> {

    val viewModel: VM

    fun initStateCollector()

    fun processViewState(viewState: D)

    fun processErrorState(errorState: ErrorEvent)

}
