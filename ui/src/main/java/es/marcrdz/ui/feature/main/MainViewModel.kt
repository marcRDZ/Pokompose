package es.marcrdz.ui.feature.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.marcrdz.presentation.base.ViewEvent
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.presentation.handlers.main.MainState
import es.marcrdz.presentation.handlers.main.MainStateHandler
import es.marcrdz.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    handler: MainStateHandler
): BaseViewModel<MainEvent, MainState>(handler) {

    override fun processViewEvent(viewEvent: ViewEvent<MainEvent>) {
        viewModelScope.launch {
            handler.handle(viewEvent) {
                viewModelScope.launch { _viewState.emit(it) }
            }
        }
    }

}