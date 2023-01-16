/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.ui.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.presentation.PresentationContract
import es.marcrdz.presentation.domain.BackgroundState
import es.marcrdz.presentation.domain.Data
import es.marcrdz.presentation.domain.Event
import es.marcrdz.presentation.domain.FailState
import es.marcrdz.presentation.domain.DataState
import es.marcrdz.presentation.domain.UIState

abstract class BaseViewModel<E : Event, D : Data>(
    initialState: BaseStateHolder<D>,
    protected val handler: PresentationContract.EventFlowHandler<E, D>
) : ViewModel() {

    private val _state: MutableState<BaseStateHolder<D>> = mutableStateOf(value = initialState)
    val state: State<BaseStateHolder<D>>
        get() = _state

    abstract fun onEvent(event: E)

    protected fun processState(
        uiState: UIState<D>,
        process: D.(newState: D) -> D = { it }
    ) {
        when (uiState) {
            is BackgroundState -> _state.value.copy(isLoading = uiState is BackgroundState.Loading)
            is FailState -> _state.value.copy(error = uiState.error)
            is DataState -> _state.value = BaseStateHolder(
                data = process.invoke(_state.value.data, uiState.data)
            )
        }
    }
}
