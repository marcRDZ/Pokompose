package es.marcrdz.data

import arrow.core.Either
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.domain.domain.ReferencePageDO

interface DataContract {

    interface PokemonDataSource {

        interface Cache {

            suspend fun getPokemonReferences(): Either<ErrorDO, ReferencePageDO<PokemonRefDO.Entity>>

            suspend fun cachePokemonReferencesPage(
                page: ReferencePageDO<PokemonRefDO.Entity>
            ): ReferencePageDO<PokemonRefDO.Entity>

        }

        interface Remote {

            suspend fun fetchPokemonReferences(
                offset: Int,
                limit: Int = 20
            ): Either<ErrorDO, ReferencePageDO<PokemonRefDO.Entity>>

            suspend fun fetchPokemon(id: Int): Either<ErrorDO, PokemonDO>
        }
    }
}