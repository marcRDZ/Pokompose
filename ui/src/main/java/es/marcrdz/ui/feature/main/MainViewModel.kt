package es.marcrdz.ui.feature.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.marcrdz.presentation.base.UserEvent
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.presentation.handlers.main.MainEventHandler
import es.marcrdz.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    handler: MainEventHandler
): BaseViewModel<MainEvent.UI, MainEvent.Data, MainStateHolder>(handler) {

    override val stateHolder: MainStateHolder = MainStateHolder()

    init {
        viewModelScope.launch {
            handler.handleInit().collect {
                stateHolder.emitViewState(it)
            }

        }
        viewModelScope.launch {
            stateHolder.viewEvents.collect { event ->
                when(event) {
                    is UserEvent ->  handler.handleEvent(event).collect { state ->
                        stateHolder.emitViewState(state)
                    }
                    else -> Unit
                }
            }
        }
    }

}