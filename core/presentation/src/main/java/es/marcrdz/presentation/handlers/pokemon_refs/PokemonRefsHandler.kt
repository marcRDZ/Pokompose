package es.marcrdz.presentation.handlers.pokemon_refs

import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.domain.domain.ReferencePageDO
import es.marcrdz.domain.usecases.FetchPokemonByIdUseCase
import es.marcrdz.domain.usecases.FetchPokemonReferencesUseCase
import es.marcrdz.domain.usecases.UseCase
import es.marcrdz.presentation.PresentationContract
import es.marcrdz.presentation.base.*
import es.marcrdz.presentation.domain.Destiny
import es.marcrdz.presentation.mappers.toFailState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface PokemonRefsHandler : PresentationContract.EventFlowHandler<PokemonRefsEvent.UI, PokemonRefsEvent.Data>

class PokemonRefsHandlerImpl @Inject constructor(
    @FetchPokemonReferencesUseCase private val fetchPokemonReferencesUC: UseCase<@JvmSuppressWildcards Nothing, @JvmSuppressWildcards ReferencePageDO<PokemonRefDO.Entity>>,
    @FetchPokemonByIdUseCase private val fetchPokemonByIdUc: UseCase<@JvmSuppressWildcards Int, @JvmSuppressWildcards PokemonDO>
) : PokemonRefsHandler {

    override suspend fun handleInit(): Flow<UIState<PokemonRefsEvent.Data>> = loadReferences()

    override suspend fun handleClear(): Flow<UIState<PokemonRefsEvent.Data>> = flow { }

    override suspend fun handleLifecycle(viewEvent: LifecycleEvent): Flow<UIState<PokemonRefsEvent.Data>> = flow {  }

    override suspend fun handleEvent(viewEvent: UserEvent<PokemonRefsEvent.UI>): Flow<UIState<PokemonRefsEvent.Data>> =
        when (viewEvent.event) {
            PokemonRefsEvent.UI.ListEndReached -> loadReferences()
            is PokemonRefsEvent.UI.PokemonSelected -> flow {
                emit(NavState(Destiny.PokemonDetail(viewEvent.event.pokemonRef)))
            }
        }

    private fun loadReferences() = flow {
        emit(BackgroundState.Loading)
        fetchPokemonReferencesUC().let { result ->
            emit(BackgroundState.Idle)
            result.mapLeft { it.toFailState() }
                .fold({ emit(it) }) {
                    emit(ViewState(PokemonRefsEvent.Data.PokemonReferencesFetched(it.results)))
                }
        }
    }

}