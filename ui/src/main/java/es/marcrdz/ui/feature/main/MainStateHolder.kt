package es.marcrdz.ui.feature.main

import es.marcrdz.presentation.base.BackgroundState
import es.marcrdz.presentation.base.FailState
import es.marcrdz.presentation.base.StateChange
import es.marcrdz.presentation.base.ViewState
import es.marcrdz.presentation.domain.PresentationReference
import es.marcrdz.presentation.handlers.main.MainReport
import es.marcrdz.ui.base.BaseStateHolder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MainStateHolder: BaseStateHolder<MainReport>() {
    private val _pokemonRefs: MutableSharedFlow<List<PresentationReference.Pokemon>> by lazy { MutableSharedFlow() }
    val pokemonRefs: SharedFlow<List<PresentationReference.Pokemon>>
        get() = _pokemonRefs.asSharedFlow()

    override suspend fun emitStateChange(state: StateChange<MainReport>) {
        when(val r = state.report) {
            is MainReport.PokemonReferencesFetched -> _pokemonRefs.emit(r.references)
            is MainReport.PokemonReferencesSelected -> TODO()
        }
    }

}
