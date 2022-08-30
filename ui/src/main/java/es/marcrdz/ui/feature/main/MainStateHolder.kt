package es.marcrdz.ui.feature.main

import android.util.Log
import es.marcrdz.presentation.domain.PresentationReference
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.ui.base.BaseStateHolder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MainStateHolder: BaseStateHolder<MainEvent.Data>() {
    private val _pokemonRefs: MutableSharedFlow<List<PresentationReference.Pokemon>> by lazy { MutableSharedFlow() }
    val pokemonRefs: SharedFlow<List<PresentationReference.Pokemon>>
        get() = _pokemonRefs.asSharedFlow()

    override suspend fun emitStateChange(event: MainEvent.Data) {
        when(event) {
            is MainEvent.Data.PokemonReferencesFetched -> {
                Log.d(this.javaClass.simpleName, "pokemons fetched... ${event.references.size}")
                _pokemonRefs.emit(event.references)
            }
            is MainEvent.Data.PokemonReferencesSelected -> TODO()
        }
    }

}
