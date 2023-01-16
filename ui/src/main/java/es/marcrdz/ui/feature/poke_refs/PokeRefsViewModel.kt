/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.ui.feature.poke_refs

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.marcrdz.presentation.handlers.main.MainData
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.presentation.handlers.main.MainEventHandler
import es.marcrdz.presentation.handlers.pokemon_refs.PokemonRefsData
import es.marcrdz.presentation.handlers.pokemon_refs.PokemonRefsEvent
import es.marcrdz.presentation.handlers.pokemon_refs.PokemonRefsHandler
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
            handler.handleInit().collect() {
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