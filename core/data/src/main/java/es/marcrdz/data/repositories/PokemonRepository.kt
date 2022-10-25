package es.marcrdz.data.repositories

import arrow.core.Either
import arrow.core.flatMap
import es.marcrdz.data.DataContract
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.ReferenceDO
import es.marcrdz.domain.domain.ReferencePageDO
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val remoteDataSource: DataContract.PokemonDataSource.Remote,
    private val cacheDataSource: DataContract.PokemonDataSource.Cache
) : DomainContract.PokemonRepository {

    override suspend fun fetchPokemonReferences(): Either<ErrorDO, ReferencePageDO<ReferenceDO.Pokemon>> {
        val cache = cacheDataSource.getPokemonReferences().orNull()
        return remoteDataSource.fetchPokemonReferences(
            offset = cache?.offset ?: 0,
            limit = cache?.limit ?: 0
        ).map { cacheDataSource.cachePokemonReferencesPage(it) }
    }

}