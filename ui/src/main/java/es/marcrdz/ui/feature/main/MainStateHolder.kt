/*
 * Copyright (c) 2023.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.ui.feature.main

import es.marcrdz.presentation.base.ViewState
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.ui.base.BaseStateHolder
import kotlinx.coroutines.CoroutineScope

class MainStateHolder(scope: CoroutineScope): BaseStateHolder<MainEvent.UI, MainEvent.Data>(scope) {

    override fun emitViewState(state: ViewState<MainEvent.Data>) {
        TODO("Not yet implemented")
    }
}