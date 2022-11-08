package es.marcrdz.domain.usecases

import arrow.core.Either
import arrow.core.left
import com.sun.net.httpserver.Authenticator.Failure
import es.marcrdz.domain.DomainContract
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.domain.domain.ReferencePageDO
import javax.inject.Inject
import javax.inject.Qualifier


@Qualifier
annotation class FetchPokemonByIdUseCase

class FetchPokemonByIdUc @Inject constructor(
    private val pokemonRepository: DomainContract.PokemonRepository
): UseCase<@JvmSuppressWildcards Int, @JvmSuppressWildcards PokemonDO> {

    override suspend fun invoke(param: Int?): Either<ErrorDO, PokemonDO> =
        param?.let {
            pokemonRepository.fetchPokemon(id = it)
        } ?: ErrorDO.NoData.left()

}
