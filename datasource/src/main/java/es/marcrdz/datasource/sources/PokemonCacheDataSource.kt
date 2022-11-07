package es.marcrdz.datasource.sources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import es.marcrdz.data.DataContract
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.domain.domain.ReferencePageDO
import javax.inject.Inject

class PokemonCacheDataSource @Inject constructor(): DataContract.PokemonDataSource.Cache {

    private var pokemonRefsCache: ReferencePageDO<PokemonRefDO.Entity>? = null

    override suspend fun getPokemonReferences(): Either<ErrorDO, ReferencePageDO<PokemonRefDO.Entity>> =
        pokemonRefsCache?.right() ?: ErrorDO.NoData.left()

    override suspend fun cachePokemonReferencesPage(page: ReferencePageDO<PokemonRefDO.Entity>): ReferencePageDO<PokemonRefDO.Entity> {
        pokemonRefsCache = pokemonRefsCache?.let {
            page.copy(
                results = listOf(
                    it.results,
                    page.results
                ).flatten()
            )
        } ?: page
        return pokemonRefsCache!!
    }

}