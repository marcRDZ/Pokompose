package es.marcrdz.presentation.handlers.main

import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.domain.domain.ReferencePageDO
import es.marcrdz.domain.usecases.FetchPokemonByIdUseCase
import es.marcrdz.domain.usecases.FetchPokemonReferencesUseCase
import es.marcrdz.domain.usecases.UseCase
import es.marcrdz.presentation.PresentationContract
import es.marcrdz.presentation.base.*
import es.marcrdz.presentation.mappers.toFailState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface MainEventHandler : PresentationContract.EventFlowHandler<MainEvent.UI, MainEvent.Data>

class MainEventHandlerImpl @Inject constructor(
    @FetchPokemonReferencesUseCase private val fetchPokemonReferencesUC: UseCase<@JvmSuppressWildcards Nothing, @JvmSuppressWildcards ReferencePageDO<PokemonRefDO.Entity>>,
    @FetchPokemonByIdUseCase private val fetchPokemonByIdUc: UseCase<@JvmSuppressWildcards Int, @JvmSuppressWildcards PokemonDO>
) : MainEventHandler {

    override suspend fun handleInit(): Flow<UIState<MainEvent.Data>> = loadReferences()

    override suspend fun handleClear(): Flow<UIState<MainEvent.Data>> = flow { }

    override suspend fun handleLifecycle(viewEvent: LifecycleEvent): Flow<UIState<MainEvent.Data>> = flow {  }

    override suspend fun handleEvent(viewEvent: UserEvent<MainEvent.UI>): Flow<UIState<MainEvent.Data>> =
        when (viewEvent.event) {
            MainEvent.UI.ListEndReached -> loadReferences()
            is MainEvent.UI.PokemonSelected -> flow {
                emit(BackgroundState.Loading)
                fetchPokemonByIdUc(param = viewEvent.event.pokemonRef.id).let { result ->
                    emit(BackgroundState.Idle)
                    result.mapLeft { it.toFailState() }
                        .fold({ emit(it) }) {
                            emit(ViewState(MainEvent.Data.PokemonReferencesSelected(it)))
                        }

                }
            }
        }

    private fun loadReferences() = flow {
        emit(BackgroundState.Loading)
        fetchPokemonReferencesUC().let { result ->
            emit(BackgroundState.Idle)
            result.mapLeft { it.toFailState() }
                .fold({ emit(it) }) {
                    emit(ViewState(MainEvent.Data.PokemonReferencesFetched(it.results)))
                }
        }
    }

}