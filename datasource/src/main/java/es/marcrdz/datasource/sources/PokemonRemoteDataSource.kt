package es.marcrdz.datasource.sources

import arrow.core.Either
import es.marcrdz.data.DataContract
import es.marcrdz.datasource.mappers.toDomain
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.ReferenceDO
import es.marcrdz.domain.domain.ReferencePageDO
import me.sargunvohra.lib.pokekotlin.client.KCPokeApi
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val pokeApi: KCPokeApi
): DataContract.PokemonDataSource {

    override suspend fun fetchPokemonReferences(
        offset: Int,
        limit: Int
    ): Either<ErrorDO, ReferencePageDO<ReferenceDO.Pokemon>> =
        pokeApi.getPokemonList(offset, limit).toDomain(transform = { ReferenceDO.Pokemon(it.id, it.name) })

}