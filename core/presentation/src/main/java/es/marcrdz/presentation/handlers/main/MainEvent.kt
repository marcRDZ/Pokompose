package es.marcrdz.presentation.handlers.main

import es.marcrdz.domain.domain.ReferenceDO
import es.marcrdz.presentation.base.Event

sealed class MainEvent {
    sealed class UI : Event {
        object ListEndReached : UI()
        class PokemonSelected(val pokemonRef: ReferenceDO.Pokemon) : UI()
    }
    sealed class Data: Event {
        class PokemonReferencesFetched(val references: List<ReferenceDO.Pokemon>) : Data()
        class PokemonReferencesSelected(val reference: ReferenceDO.Pokemon) : Data()
    }
}