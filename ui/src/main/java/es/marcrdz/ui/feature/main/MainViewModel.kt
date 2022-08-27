package es.marcrdz.ui.feature.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.marcrdz.presentation.base.ViewEvent
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.presentation.handlers.main.MainReport
import es.marcrdz.presentation.handlers.main.MainStateHandler
import es.marcrdz.ui.base.BaseStateHolder
import es.marcrdz.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    handler: MainStateHandler
): BaseViewModel<MainEvent, MainReport>(handler) {

    override val stateHolder: BaseStateHolder<MainReport> = MainStateHolder()

    init {
        viewModelScope.launch {
            handler.handleInit { stateHolder.emit(it) }
        }
    }

    override fun processViewEvent(viewEvent: ViewEvent<MainEvent>) {
        viewModelScope.launch {
            handler.handleEvent(viewEvent) { stateHolder.emit(it) }
        }
    }

}