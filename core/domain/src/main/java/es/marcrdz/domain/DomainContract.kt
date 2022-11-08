package es.marcrdz.domain

import arrow.core.Either
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.domain.domain.ReferencePageDO

interface DomainContract {

    interface PokemonRepository {

        suspend fun getOrFetchPokemonReferencesPaginated(): Either<ErrorDO, ReferencePageDO<PokemonRefDO.Entity>>

        suspend fun fetchPokemon(id: Int): Either<ErrorDO, PokemonDO>

    }

}