package es.marcrdz.presentation.handlers.pokemon_detail

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

interface PokemonDetailHandler : PresentationContract.EventFlowHandler<PokemonDetailEvent.UI, PokemonDetailEvent.Data>

class PokemonDetailHandlerImpl @Inject constructor(
    @FetchPokemonByIdUseCase private val fetchPokemonByIdUc: UseCase<@JvmSuppressWildcards Int, @JvmSuppressWildcards PokemonDO>
) : PokemonDetailHandler {

    override suspend fun handleInit(): Flow<UIState<PokemonDetailEvent.Data>> {
       /*
       * emit(BackgroundState.Loading)
                fetchPokemonByIdUc(param = viewEvent.event.pokemonRef.id).let { result ->
                    emit(BackgroundState.Idle)
                    result.mapLeft { it.toFailState() }
                        .fold({ emit(it) }) {
                            emit(ViewState(PokemonRefsEvent.Data.PokemonFetched(it)))
                        }

                }
       * */
        TODO("Not yet implemented")
    }

    override suspend fun handleClear(): Flow<UIState<PokemonDetailEvent.Data>> {
        TODO("Not yet implemented")
    }

    override suspend fun handleLifecycle(viewEvent: LifecycleEvent): Flow<UIState<PokemonDetailEvent.Data>> {
        TODO("Not yet implemented")
    }

    override suspend fun handleEvent(viewEvent: UserEvent<PokemonDetailEvent.UI>): Flow<UIState<PokemonDetailEvent.Data>> {
        TODO("Not yet implemented")
    }
}