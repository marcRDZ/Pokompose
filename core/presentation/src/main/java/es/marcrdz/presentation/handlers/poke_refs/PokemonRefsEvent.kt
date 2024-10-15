/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.presentation.handlers.poke_refs

import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.presentation.domain.Data
import es.marcrdz.presentation.domain.Event

sealed class PokemonRefsEvent : Event {
    data object OnInit : PokemonRefsEvent()
    data object OnEndListReached : PokemonRefsEvent()
    class OnPokemonSelected(val pokemonRef: PokemonRefDO.Entity) : PokemonRefsEvent()
}

data class PokemonRefsData(
    val references: List<PokemonRefDO.Entity> = emptyList()
) : Data