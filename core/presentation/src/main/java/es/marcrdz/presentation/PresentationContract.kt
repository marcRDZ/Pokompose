/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.presentation

import es.marcrdz.presentation.domain.Data
import es.marcrdz.presentation.domain.Event
import es.marcrdz.presentation.domain.UIState
import kotlinx.coroutines.flow.Flow

interface PresentationContract {

    interface EventFlowHandler<in I : Event, out O : Data> {

        suspend fun handleInit(): Flow<UIState<O>>

        suspend fun handleEvent(event: I): Flow<UIState<O>>

    }

}
