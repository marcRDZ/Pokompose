/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.ui.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.ui.composables.PokeScaffold
import es.marcrdz.ui.feature.poke_refs.PokeRefsScreen


@Composable
fun MainScreen(
    viewModel: MainViewModel
) {

    PokeScaffold(
        isLoading = viewModel.state.value.isLoading,
        error = viewModel.state.value.error,
        modifier = Modifier
    ) { paddingValues ->

        PokeRefsScreen(
            viewModel = hiltViewModel(),
            modifier = Modifier.padding(paddingValues),
            onLoading = { viewModel.onEvent(MainEvent.OnChildWorking(it)) },
            onFail = { it?.let { viewModel.onEvent(MainEvent.OnChildFail(it)) } },
            onNavigate = {}
        )
    }
}
