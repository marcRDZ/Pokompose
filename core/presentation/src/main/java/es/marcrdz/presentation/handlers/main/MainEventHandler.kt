package es.marcrdz.presentation.handlers.main

import es.marcrdz.domain.domain.DomainReference
import es.marcrdz.domain.domain.DomainReferencePage
import es.marcrdz.domain.usecases.FetchPokemonReferencesUseCase
import es.marcrdz.domain.usecases.UseCase
import es.marcrdz.presentation.PresentationContract
import es.marcrdz.presentation.base.*
import es.marcrdz.presentation.domain.PresentationReference
import es.marcrdz.presentation.mappers.toPresentation
import javax.inject.Inject

interface MainEventHandler : PresentationContract.EventHandler<ViewEvent<MainEvent.UI>, ViewState<MainEvent.Data>>

class MainEventHandlerImpl @Inject constructor(
    @FetchPokemonReferencesUseCase private val fetchPokemonReferencesUC: UseCase<@JvmSuppressWildcards Nothing, @JvmSuppressWildcards DomainReferencePage<DomainReference.Pokemon>>
) : MainEventHandler {

    override suspend fun handleInit(viewState: suspend (ViewState<MainEvent.Data>) -> Unit) {
        viewState(BackgroundState(BackgroundEvent.Loading))
        fetchPokemonReferencesUC().let { result ->
            viewState(BackgroundState(BackgroundEvent.Idle))
            result.toPresentation { PresentationReference.Pokemon(it.id, it.name) }
                .fold({ viewState(FailState(it)) }) {
                    viewState(StateChange(MainEvent.Data.PokemonReferencesFetched(it.results)))
                }
        }
    }

    override suspend fun handleEvent(
        viewEvent: ViewEvent<MainEvent.UI>,
        viewState: suspend (ViewState<MainEvent.Data>) -> Unit
    ) {
        when(viewEvent) {
            is UserEvent -> when (viewEvent.event) {
                MainEvent.UI.ListEndReached -> TODO()
                is MainEvent.UI.PokemonSelected -> TODO()
            }
            is Lifecycle -> Unit
        }


    }

}