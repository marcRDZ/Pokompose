/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.data.repositories

import arrow.core.Either
import es.marcrdz.data.DataContract
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.domain.domain.ReferencePageDO
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val remoteDataSource: DataContract.PokemonDataSource.Remote,
    private val cacheDataSource: DataContract.PokemonDataSource.Cache
) : DomainContract.PokemonRepository {

    override suspend fun getOrFetchPokemonReferencesPaginated(): Either<ErrorDO, ReferencePageDO<PokemonRefDO.Entity>> {
        val cache = cacheDataSource.getPokemonReferences().orNull()
        return remoteDataSource.fetchPokemonReferences(
            offset = cache?.offset ?: 0,
            limit = cache?.limit ?: 0
        ).map { cacheDataSource.cachePokemonReferencesPage(it) }
    }

    override suspend fun fetchPokemon(id: Int): Either<ErrorDO, PokemonDO> =
        remoteDataSource.fetchPokemon(id)

}
