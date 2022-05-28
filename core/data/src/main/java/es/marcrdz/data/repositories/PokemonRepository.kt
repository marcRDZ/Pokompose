package es.marcrdz.data.repositories

import arrow.core.Either
import es.marcrdz.data.DataContract
import es.marcrdz.data.mappers.toDomain
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.domain.DomainError
import es.marcrdz.domain.domain.DomainReference
import es.marcrdz.domain.domain.DomainReferencePage
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val remoteDataSource: DataContract.PokemonDataSource
) : DomainContract.PokemonRepository {

    override suspend fun fetchPokemonReferences(
        offset: Int,
        limit: Int
    ): Either<DomainError, DomainReferencePage<DomainReference.Pokemon>> =
        remoteDataSource.fetchPokemonReferences(offset, limit).toDomain { DomainReference.Pokemon(it.id, it.name) }

}