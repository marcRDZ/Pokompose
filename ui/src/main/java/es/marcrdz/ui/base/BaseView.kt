package es.marcrdz.ui.base

import es.marcrdz.presentation.base.Event
import es.marcrdz.presentation.base.State
import es.marcrdz.presentation.base.ViewState

interface BaseView<E : Event, S : State> {

    val viewModel: BaseViewModel<E, S>

    fun initStateCollector()

    fun processViewState(viewState: S)

}
