/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.ui.feature.poke_refs

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.marcrdz.presentation.handlers.poke_refs.PokemonRefsData
import es.marcrdz.presentation.handlers.poke_refs.PokemonRefsEvent
import es.marcrdz.presentation.handlers.poke_refs.PokemonRefsHandler
import es.marcrdz.ui.base.BaseStateHolder
import es.marcrdz.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeRefsViewModel @Inject constructor(
    handler: PokemonRefsHandler
): BaseViewModel<PokemonRefsEvent, PokemonRefsData>(
    initialState = BaseStateHolder(data = PokemonRefsData()),
    handler = handler
) {

    init {
        viewModelScope.launch {
            handler.handleEvent(PokemonRefsEvent.OnInit).collect() {
                processState(it)
            }
        }
    }

    override fun onEvent(event: PokemonRefsEvent) {
        viewModelScope.launch {
            handler.handleEvent(event).collect() {
                processState(it)
            }
        }
    }
}