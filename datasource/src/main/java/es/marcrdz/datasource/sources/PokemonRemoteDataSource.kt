/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.datasource.sources

import arrow.core.Either
import es.marcrdz.data.DataContract
import es.marcrdz.datasource.mappers.errorDO
import es.marcrdz.datasource.mappers.pokemonDO
import es.marcrdz.datasource.mappers.toDomain
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.domain.domain.ReferencePageDO
import me.sargunvohra.lib.pokekotlin.client.KCPokeApi
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val pokeApi: KCPokeApi
): DataContract.PokemonDataSource.Remote {

    override suspend fun fetchPokemonReferences(
        offset: Int,
        limit: Int
    ): Either<ErrorDO, ReferencePageDO<PokemonRefDO.Entity>> =
        pokeApi.getPokemonList(offset, limit).toDomain(transform = { PokemonRefDO.Entity(it.id, it.name) })


    override suspend fun fetchPokemon(id: Int): Either<ErrorDO, PokemonDO> =
        pokeApi.getPokemon(id).bimap({ errorDO(it) }, { pokemonDO(it) })

}
