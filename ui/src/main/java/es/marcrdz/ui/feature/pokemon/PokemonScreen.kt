/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.ui.feature.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.imageLoader
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.domain.domain.PokemonSpriteUrlBuilder
import es.marcrdz.ui.R
import es.marcrdz.ui.composables.PokemonDetail
import es.marcrdz.ui.composables.SimpleLabelledContentView
import es.marcrdz.ui.domain.Destiny

@Composable
fun PokemonScreen(
    viewModel: PokemonViewModel,
    modifier: Modifier,
    onLoading: (Boolean) -> Unit,
    onFail: (ErrorDO?) -> Unit,
    onNavigate: (Destiny?) -> Unit

) {

    LaunchedEffect(key1 = viewModel.state.value.isLoading) {
        onLoading(viewModel.state.value.isLoading)
    }

    LaunchedEffect(key1 = viewModel.state.value.error) {
        onFail(viewModel.state.value.error)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 24.dp)
    ) {

        Text(
            modifier = Modifier
                .padding(all = 24.dp),
            text = viewModel.destiny.name.replaceFirstChar { it.uppercase() },
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        viewModel.state.value.data.pokemon?.let {
            PokemonDetail(pokemon = it)
        }

    }
}
