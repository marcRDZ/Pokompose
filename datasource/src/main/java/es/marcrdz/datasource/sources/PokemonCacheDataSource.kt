package es.marcrdz.datasource.sources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import es.marcrdz.data.DataContract
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.ReferenceDO
import es.marcrdz.domain.domain.ReferencePageDO
import javax.inject.Inject

class PokemonCacheDataSource @Inject constructor(): DataContract.PokemonDataSource.Cache {

    private var pokemonRefsCache: ReferencePageDO<ReferenceDO.Pokemon>? = null

    override suspend fun getPokemonReferences(): Either<ErrorDO, ReferencePageDO<ReferenceDO.Pokemon>> =
        pokemonRefsCache?.right() ?: ErrorDO.NoData.left()

    override suspend fun cachePokemonReferencesPage(page: ReferencePageDO<ReferenceDO.Pokemon>): ReferencePageDO<ReferenceDO.Pokemon> {
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