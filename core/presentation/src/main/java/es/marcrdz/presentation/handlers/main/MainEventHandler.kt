package es.marcrdz.presentation.handlers.main

import es.marcrdz.domain.domain.ReferenceDO
import es.marcrdz.domain.domain.ReferencePageDO
import es.marcrdz.domain.usecases.FetchPokemonReferencesUseCase
import es.marcrdz.domain.usecases.UseCase
import es.marcrdz.presentation.PresentationContract
import es.marcrdz.presentation.base.*
import es.marcrdz.presentation.mappers.toFailState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface MainEventHandler : PresentationContract.EventFlowHandler<UserEvent<MainEvent.UI>, ViewState<MainEvent.Data>>

class MainEventHandlerImpl @Inject constructor(
    @FetchPokemonReferencesUseCase private val fetchPokemonReferencesUC: UseCase<@JvmSuppressWildcards Nothing, @JvmSuppressWildcards ReferencePageDO<ReferenceDO.Pokemon>>
) : MainEventHandler {

    override suspend fun handleInit(): Flow<ViewState<MainEvent.Data>> = loadReferences()

    override suspend fun handleClear(): Flow<ViewState<MainEvent.Data>> = flow {  }

    override suspend fun handleEvent(viewEvent: UserEvent<MainEvent.UI>): Flow<ViewState<MainEvent.Data>> =
        when (viewEvent.event) {
            MainEvent.UI.ListEndReached -> loadReferences()
            is MainEvent.UI.PokemonSelected -> flow {  }
        }

    private fun loadReferences() = flow {
        emit(BackgroundState.Loading)
        fetchPokemonReferencesUC().let { result ->
            emit(BackgroundState.Idle)
            result
                .mapLeft { it.toFailState() }
                .fold({ emit(it) }) {
                    emit(StateChange(MainEvent.Data.PokemonReferencesFetched(it.results)))
                }
        }
    }

}