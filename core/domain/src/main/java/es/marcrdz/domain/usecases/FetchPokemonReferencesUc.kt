package es.marcrdz.domain.usecases

import arrow.core.Either
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.domain.DomainError
import es.marcrdz.domain.domain.DomainReference
import es.marcrdz.domain.domain.DomainReferencePage
import javax.inject.Inject
import javax.inject.Qualifier

@Qualifier
annotation class FetchPokemonReferencesUseCase

class FetchPokemonReferencesUc @Inject constructor(
    private val pokemonRepository: DomainContract.PokemonRepository
): UseCase<@JvmSuppressWildcards Any, @JvmSuppressWildcards DomainReferencePage<DomainReference.Pokemon>> {

    override suspend fun invoke(param: Any?): Either<DomainError,  DomainReferencePage<DomainReference.Pokemon>> =
        pokemonRepository.fetchPokemonReferences()

}
