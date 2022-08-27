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

interface MainStateHandler : PresentationContract.EventHandler<ViewEvent<MainEvent>, ViewState<MainReport>>

class MainStateHandlerImpl @Inject constructor(
    @FetchPokemonReferencesUseCase private val fetchPokemonReferencesUC: UseCase<@JvmSuppressWildcards Nothing, @JvmSuppressWildcards DomainReferencePage<DomainReference.Pokemon>>
) : MainStateHandler {

    override suspend fun handleInit(viewState: suspend (ViewState<MainReport>) -> Unit) {
        viewState(BackgroundState(BackgroundReport.Loading))
        fetchPokemonReferencesUC().let { result ->
            viewState(BackgroundState(BackgroundReport.Idle))
            result.toPresentation { PresentationReference.Pokemon(it.id, it.name) }
                .fold({ viewState(FailState(it)) }) {
                    viewState(StateChange(MainReport.PokemonReferencesFetched(it.results)))
                }
        }
    }

    override suspend fun handleEvent(
        viewEvent: ViewEvent<MainEvent>,
        viewState: suspend (ViewState<MainReport>) -> Unit
    ) {
        when(viewEvent) {
            is UserEvent -> when (viewEvent.event) {
                MainEvent.ListEndReached -> TODO()
                is MainEvent.PokemonSelected -> TODO()
            }
            is Lifecycle -> Unit
        }


    }

}