/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.presentation.handlers.pokemon_detail

import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.presentation.domain.Data
import es.marcrdz.presentation.domain.Event

sealed class PokemonDetailEvent : Event

data class PokemonDetailData(val pokemon: PokemonDO) : Data
