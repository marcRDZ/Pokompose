package es.marcrdz.presentation.handlers.pokemon_detail

import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.presentation.base.Event

class PokemonDetailEvent {
    sealed class UI : Event {}
    sealed class Data: Event {
        class PokemonFetched(val pokemon: PokemonDO) : Data()
    }
}