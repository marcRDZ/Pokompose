package es.marcrdz.data

import arrow.core.Either
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.ReferenceDO
import es.marcrdz.domain.domain.ReferencePageDO

interface DataContract {

    interface PokemonDataSource {
        suspend fun fetchPokemonReferences(offset: Int, limit: Int): Either<ErrorDO, ReferencePageDO<ReferenceDO.Pokemon>>
    }
}