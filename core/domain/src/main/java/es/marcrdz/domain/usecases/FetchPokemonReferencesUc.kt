package es.marcrdz.domain.usecases

import arrow.core.Either
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.domain.domain.ReferencePageDO
import javax.inject.Inject
import javax.inject.Qualifier

@Qualifier
annotation class FetchPokemonReferencesUseCase

class FetchPokemonReferencesUc @Inject constructor(
    private val pokemonRepository: DomainContract.PokemonRepository
): UseCase<@JvmSuppressWildcards Any, @JvmSuppressWildcards ReferencePageDO<PokemonRefDO.Entity>> {

    override suspend fun invoke(param: Any?): Either<ErrorDO,  ReferencePageDO<PokemonRefDO.Entity>> =
        pokemonRepository.getOrFetchPokemonReferencesPaginated()

}
