/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.presentation.domain

import es.marcrdz.domain.domain.ErrorDO

interface Event

interface Data

sealed class UIState<out T : Data>

sealed class BackgroundState : UIState<Nothing>() {
    data object Idle : BackgroundState()
    data object Loading : BackgroundState()
}

data class FailState(val error: ErrorDO) : UIState<Nothing>()

data class DataState<out T : Data>(val data: T) : UIState<T>()
