package es.marcrdz.domain

import arrow.core.Either
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.domain.domain.ReferencePageDO

interface DomainContract {

    interface PokemonRepository {

        suspend fun fetchPokemonReferences(): Either<ErrorDO, ReferencePageDO<PokemonRefDO.Entity>>

    }

}