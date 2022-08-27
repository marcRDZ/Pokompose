package es.marcrdz.presentation.handlers.main

import es.marcrdz.presentation.base.Event
import es.marcrdz.presentation.domain.PresentationReference

sealed class MainEvent: Event() {
    object ListEndReached: MainEvent()
    class PokemonSelected(val pokemonRef: PresentationReference.Pokemon): MainEvent()
}