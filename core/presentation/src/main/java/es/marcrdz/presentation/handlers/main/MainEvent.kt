/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.presentation.handlers.main

import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.presentation.domain.Data
import es.marcrdz.presentation.domain.Destiny
import es.marcrdz.presentation.domain.Event

sealed class MainEvent : Event {
    class OnChildWorking(val isLoading: Boolean) : MainEvent()
    class OnChildFail(val error: ErrorDO) : MainEvent()
    class PokemonSelected(val pokemonRef: PokemonRefDO.Entity) : MainEvent()
}

data class MainData(
    val destiny: Destiny = Destiny.PokemonRefs
) : Data