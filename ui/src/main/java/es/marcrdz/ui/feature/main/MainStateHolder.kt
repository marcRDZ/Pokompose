package es.marcrdz.ui.feature.main

import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.presentation.base.ViewEvent
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.ui.base.BaseStateHolder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MainStateHolder : BaseStateHolder<MainEvent.Data>() {
    private val _viewEvents: MutableSharedFlow<ViewEvent<MainEvent.UI>> by lazy { MutableSharedFlow() }
    val viewEvents: SharedFlow<ViewEvent<MainEvent.UI>>
        get() = _viewEvents.asSharedFlow()

    private val _pokemonRefs: MutableSharedFlow<List<PokemonRefDO.Entity>> by lazy { MutableSharedFlow() }
    val pokemonRefs: SharedFlow<List<PokemonRefDO.Entity>>
        get() = _pokemonRefs.asSharedFlow()

    override suspend fun emitStateChange(event: MainEvent.Data) {
        when (event) {
            is MainEvent.Data.PokemonReferencesFetched -> _pokemonRefs.emit(event.references)
            is MainEvent.Data.PokemonReferencesSelected -> TODO()
        }
    }

    suspend fun emitViewEvent(event: ViewEvent<MainEvent.UI>) {
        _viewEvents.emit(event)
    }

}
