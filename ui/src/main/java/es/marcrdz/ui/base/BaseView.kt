package es.marcrdz.ui.base

import es.marcrdz.presentation.base.ErrorReport
import es.marcrdz.presentation.base.Event
import es.marcrdz.presentation.base.Report

interface BaseView<E : Event, R : Report, SH: BaseStateHolder<R>, VM:  BaseViewModel<E, R, SH>> {

    val viewModel: VM

    fun initStateCollector()

    fun processViewState(viewState: R)

    fun processErrorState(errorState: ErrorReport)

}
