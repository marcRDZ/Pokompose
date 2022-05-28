package es.marcrdz.datasource.sources

import arrow.core.Either
import es.marcrdz.data.DataContract
import es.marcrdz.data.domain.DataError
import es.marcrdz.data.domain.DataReference
import es.marcrdz.data.domain.DataReferencePage
import es.marcrdz.datasource.mappers.toData
import me.sargunvohra.lib.pokekotlin.client.KCPokeApi
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val pokeApi: KCPokeApi
): DataContract.PokemonDataSource {

    override suspend fun fetchPokemonReferences(
        offset: Int,
        limit: Int
    ): Either<DataError, DataReferencePage<DataReference.Pokemon>> =
        pokeApi.getPokemonList(offset, limit).toData(transform = { DataReference.Pokemon(it.id, it.name) })

}