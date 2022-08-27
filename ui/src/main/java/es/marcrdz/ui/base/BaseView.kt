package es.marcrdz.ui.base

import es.marcrdz.presentation.base.ErrorReport
import es.marcrdz.presentation.base.Event
import es.marcrdz.presentation.base.Report

interface BaseView<E : Event, S : Report> {

    val viewModel: BaseViewModel<E, S>

    fun initStateCollector()

    fun processViewState(viewState: S)

    fun processErrorState(errorState: ErrorReport)

}
