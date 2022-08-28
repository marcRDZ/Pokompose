package es.marcrdz.ui.feature.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.marcrdz.presentation.base.ViewEvent
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.presentation.handlers.main.MainReport
import es.marcrdz.presentation.handlers.main.MainEventHandler
import es.marcrdz.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    handler: MainEventHandler
): BaseViewModel<MainEvent, MainReport, MainStateHolder>(handler) {

    override val stateHolder: MainStateHolder = MainStateHolder()

    init {
        viewModelScope.launch {
            handler.handleInit { stateHolder.emitViewState(it) }
        }
    }

    override fun processViewEvent(viewEvent: ViewEvent<MainEvent>) {
        viewModelScope.launch {
            handler.handleEvent(viewEvent) { stateHolder.emitViewState(it) }
        }
    }

}