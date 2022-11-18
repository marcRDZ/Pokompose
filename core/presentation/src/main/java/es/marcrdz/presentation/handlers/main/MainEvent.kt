package es.marcrdz.presentation.handlers.main

import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.presentation.base.Event

sealed class MainEvent {
    sealed class UI : Event {
        object ListEndReached : UI()
        class PokemonSelected(val pokemonRef: PokemonRefDO.Entity) : UI()
    }
    sealed class Data: Event {
        class PokemonReferencesFetched(val references: List<PokemonRefDO.Entity>) : Data()
        class PokemonReferencesSelected(val pokemon: PokemonDO) : Data()
    }
}