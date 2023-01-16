package es.marcrdz.presentation.domain

import es.marcrdz.domain.domain.PokemonRefDO

sealed class Destiny(
    val id: String
) {
    object PokemonRefs: Destiny("pokemon_refs")
    data class PokemonDetail(val pokeRef: PokemonRefDO.Entity): Destiny("pokemon_detail")

}