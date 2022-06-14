package es.marcrdz.presentation.handlers.main

import es.marcrdz.domain.domain.DomainReference
import es.marcrdz.domain.usecases.FetchPokemonReferencesUseCase
import es.marcrdz.domain.usecases.UseCase
import es.marcrdz.presentation.PresentationContract
import es.marcrdz.presentation.base.ViewEvent
import es.marcrdz.presentation.base.ViewState
import es.marcrdz.presentation.mappers.toErrorState
import javax.inject.Inject

interface MainStateHandler : PresentationContract.StateHandler<ViewEvent<MainEvent>, ViewState<MainState>>

class MainStateHandlerImpl @Inject constructor(
    @FetchPokemonReferencesUseCase private val fetchPokemonReferencesUC: UseCase<@JvmSuppressWildcards Nothing, @JvmSuppressWildcards List<DomainReference.Pokemon>>
) : MainStateHandler {

    override suspend fun handle(
        viewEvent: ViewEvent<MainEvent>,
        viewState: (ViewState<MainState>) -> Unit
    ) {
        when (viewEvent) {
            ViewEvent.Lifecycle.OnStart -> {
                viewState(ViewState.Loading)
                fetchPokemonReferencesUC().let { result ->
                    viewState(ViewState.Idle)
                    result.fold({ viewState(ViewState.Fail(it.toErrorState())) })
                    { viewState(ViewState.StateChange(MainState.PokemonReferencesFetched(it))) }
                }
            }
            is ViewEvent.UserAction -> {
                viewState(ViewState.Loading)
                processMainEvent(event = viewEvent.event)
            }
            else -> Unit
        }
    }

    private fun processMainEvent(event: MainEvent): ViewState<MainState> {
        when (event) {
            MainEvent.Continue -> TODO()
            is MainEvent.LogIn -> TODO()
        }
    }
}