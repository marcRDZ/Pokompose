package es.marcrdz.domain

import arrow.core.Either
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.ReferenceDO
import es.marcrdz.domain.domain.ReferencePageDO

interface DomainContract {

    interface PokemonRepository {
        suspend fun fetchPokemonReferences(offset: Int = 0, limit: Int = 0): Either<ErrorDO, ReferencePageDO<ReferenceDO.Pokemon>>
    }
}