package es.marcrdz.data

import arrow.core.Either
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.ReferenceDO
import es.marcrdz.domain.domain.ReferencePageDO

interface DataContract {

    interface PokemonDataSource {

        interface Cache {
            suspend fun getPokemonReferences(): Either<ErrorDO, ReferencePageDO<ReferenceDO.Pokemon>>
            suspend fun cachePokemonReferencesPage(
                page: ReferencePageDO<ReferenceDO.Pokemon>
            ): ReferencePageDO<ReferenceDO.Pokemon>
        }

        interface Remote {
            suspend fun fetchPokemonReferences(
                offset: Int,
                limit: Int = 20
            ): Either<ErrorDO, ReferencePageDO<ReferenceDO.Pokemon>>
        }
    }
}