package es.marcrdz.ui.composables.main

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import es.marcrdz.presentation.base.BackgroundState
import es.marcrdz.presentation.base.UserEvent
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.ui.base.BaseStateHolder
import es.marcrdz.ui.composables.PokeScaffold
import es.marcrdz.ui.composables.PokemonRefItemList
import kotlinx.coroutines.CoroutineScope


@Composable
fun MainContent(
    stateHolder: BaseStateHolder<MainEvent.UI, MainEvent.Data>,
    modifier: Modifier = Modifier,
    scope: CoroutineScope = rememberCoroutineScope(),
) {

    val fail by stateHolder.failState.collectAsState(initial = null)
    val loading by stateHolder.backgroundState.collectAsState(initial = BackgroundState.Idle)

    PokeScaffold(
        backgroundState = loading,
        failState = fail,
        modifier = modifier
    ) {
        PokemonRefItemList(
            refs = emptyList(),
            modifier = modifier.padding(it),
            onEndReached = { /*stateHolder.emitViewEvent(UserEvent(MainEvent.UI.PokemonSelected(0)))*/ },
            onItemSelected = {}
        )
    }
}
