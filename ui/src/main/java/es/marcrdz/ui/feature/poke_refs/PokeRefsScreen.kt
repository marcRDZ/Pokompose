/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.ui.feature.poke_refs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.presentation.handlers.poke_refs.PokemonRefsEvent
import es.marcrdz.ui.composables.PokemonRefItemList

@Composable
fun PokeRefsScreen(
    viewModel: PokeRefsViewModel,
    modifier: Modifier,
    onLoading: (Boolean) -> Unit,
    onFail: (ErrorDO?) -> Unit,
    onNavigate: (PokemonRefDO.Entity) -> Unit
) {

    LaunchedEffect(key1 = viewModel.state.value.isLoading) {
        onLoading(viewModel.state.value.isLoading)
    }

    LaunchedEffect(key1 = viewModel.state.value.error) {
        onFail(viewModel.state.value.error)
    }

    PokemonRefItemList(
        refs = viewModel.state.value.data.references,
        modifier = modifier,
        onEndReached = { viewModel.onEvent(PokemonRefsEvent.OnEndListReached) },
        onItemSelected = { onNavigate(it) }
    )

}