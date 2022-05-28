package es.marcrdz.domain.usecases

import arrow.core.Either
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.domain.DomainError
import es.marcrdz.domain.domain.DomainReference
import javax.inject.Inject
import javax.inject.Qualifier

@Qualifier
annotation class FetchPokemonReferencesUseCase

class FetchPokemonReferencesUc @Inject constructor(
    private val pokemonRepository: DomainContract.PokemonRepository
): UseCase<@JvmSuppressWildcards Any, @JvmSuppressWildcards List<DomainReference.Pokemon>> {

    override suspend fun invoke(param: Any?): Either<DomainError, List<DomainReference.Pokemon>> =
        pokemonRepository.fetchPokemonReferences().map { it.results }

}
