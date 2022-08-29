package es.marcrdz.presentation.handlers.main

import es.marcrdz.presentation.base.Event
import es.marcrdz.presentation.domain.PresentationReference

sealed class MainEvent {
    sealed class UI : Event {
        object ListEndReached : UI()
        class PokemonSelected(val pokemonRef: PresentationReference.Pokemon) : UI()
    }
    sealed class Data: Event {
        class PokemonReferencesFetched(val references: List<PresentationReference.Pokemon>) : Data()
        class PokemonReferencesSelected(val reference: PresentationReference.Pokemon) : Data()
    }
}