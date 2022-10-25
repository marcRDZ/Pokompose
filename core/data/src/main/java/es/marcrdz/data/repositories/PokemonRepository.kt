package es.marcrdz.data.repositories

import arrow.core.Either
import es.marcrdz.data.DataContract
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.ReferenceDO
import es.marcrdz.domain.domain.ReferencePageDO
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val remoteDataSource: DataContract.PokemonDataSource
) : DomainContract.PokemonRepository {

    override suspend fun fetchPokemonReferences(
        offset: Int,
        limit: Int
    ): Either<ErrorDO, ReferencePageDO<ReferenceDO.Pokemon>> =
        remoteDataSource.fetchPokemonReferences(offset, limit)

}