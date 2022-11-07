package es.marcrdz.ui.composables.main

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import es.marcrdz.presentation.base.UserEvent
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.ui.composables.PokeScaffold
import es.marcrdz.ui.composables.PokemonRefItemList
import es.marcrdz.ui.feature.main.MainStateHolder
import kotlinx.coroutines.CoroutineScope


@Composable
fun MainContent(
    stateHolder: MainStateHolder,
    modifier: Modifier = Modifier,
    scope: CoroutineScope = rememberCoroutineScope(),
) {

    PokeScaffold(stateHolder = stateHolder, modifier = modifier) {
        val pokeRefs by stateHolder.pokemonRefs.collectAsState(initial = emptyList())
        PokemonRefItemList(
            refs = pokeRefs,
            modifier = modifier.padding(it),
            onEndReached = { stateHolder.emitViewEvent(UserEvent(MainEvent.UI.ListEndReached)) }
        )
    }
}
