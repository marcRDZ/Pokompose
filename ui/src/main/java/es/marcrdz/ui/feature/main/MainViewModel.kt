package es.marcrdz.ui.feature.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.marcrdz.presentation.base.UserEvent
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.presentation.handlers.main.MainEventHandler
import es.marcrdz.ui.base.BaseStateHolder
import es.marcrdz.ui.base.BaseViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    handler: MainEventHandler
): BaseViewModel<MainEvent.UI, MainEvent.Data>(handler) {

    override val stateHolder = MainStateHolder(scope = viewModelScope)

    init {
        viewModelScope.launch {
            handler.handleInit().onEach {
                stateHolder.emitUIState(it)
            }
        }
        viewModelScope.launch {
            stateHolder.uiEvents.collect { event ->
                when(event) {
                    is UserEvent ->  handler.handleEvent(event).onEach { state ->
                        stateHolder.emitUIState(state)
                    }
                    else -> Unit
                }
            }
        }
    }

}