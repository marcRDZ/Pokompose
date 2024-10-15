/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.ui.feature.pokemon

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import es.marcrdz.presentation.handlers.pokemon.PokemonData
import es.marcrdz.presentation.handlers.pokemon.PokemonEvent
import es.marcrdz.presentation.handlers.pokemon.PokemonHandler
import es.marcrdz.ui.base.BaseStateHolder
import es.marcrdz.ui.base.BaseViewModel
import es.marcrdz.ui.domain.Destiny
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    handler: PokemonHandler,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<PokemonEvent, PokemonData>(
    initialState = BaseStateHolder(data = PokemonData()),
    handler = handler
) {

    val destiny: Destiny.Pokemon = savedStateHandle.toRoute()

    init {
        viewModelScope.launch {
            handler.handleEvent(
                event = PokemonEvent.OnInit(destiny.id)
            ).collect() { processState(it) }
        }
    }

    override fun onEvent(event: PokemonEvent) {
        viewModelScope.launch {
            handler.handleEvent(event).collect() {
                processState(it)
            }
        }
    }

}