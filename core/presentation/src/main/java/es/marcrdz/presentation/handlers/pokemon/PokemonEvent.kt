/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.presentation.handlers.pokemon

import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.presentation.domain.Data
import es.marcrdz.presentation.domain.Event

sealed class PokemonEvent : Event {
    data class OnInit(val id: Int): PokemonEvent()
}

data class PokemonData(
    val pokemon: PokemonDO? = null
) : Data
