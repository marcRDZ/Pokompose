package es.marcrdz.data

import arrow.core.Either
import es.marcrdz.data.domain.DataError
import es.marcrdz.data.domain.DataReference
import es.marcrdz.data.domain.DataReferencePage

interface DataContract {

    interface PokemonDataSource {
        suspend fun fetchPokemonReferences(offset: Int, limit: Int): Either<DataError, DataReferencePage<DataReference.Pokemon>>
    }
}