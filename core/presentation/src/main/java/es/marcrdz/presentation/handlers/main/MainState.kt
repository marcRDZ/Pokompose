package es.marcrdz.presentation.handlers.main

import es.marcrdz.domain.domain.DomainReference
import es.marcrdz.presentation.base.State


sealed class MainState : State() {

    class PokemonReferencesFetched(val references: List<DomainReference.Pokemon>) : MainState()
}