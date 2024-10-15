/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.imageLoader
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.domain.domain.PokemonSpriteUrlBuilder
import es.marcrdz.ui.R
import es.marcrdz.ui.theme.PokomposeTheme


@Composable
fun PokemonRefItemList(
    refs: List<PokemonRefDO.Entity>,
    onEndReached: () -> Unit,
    onItemSelected: (PokemonRefDO.Entity) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        refs.takeIf { it.isNotEmpty() }?.let {
            items(count = refs.size) { index ->
                PokemonRefItem(
                    item = refs[index],
                    Modifier
                        .height(72.dp)
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 16.dp)
                ) {
                    onItemSelected(it)
                }
            }
            item {
                LaunchedEffect(refs.size) {
                    onEndReached.invoke()
                }
            }
        }

    }
}

@Composable
fun PokemonRefItem(
    item: PokemonRefDO.Entity,
    modifier: Modifier = Modifier,
    onItemSelected: (PokemonRefDO.Entity) -> Unit
) {
    val imgLoader = LocalContext.current.imageLoader

    Card(
        modifier = modifier
            .clickable { onItemSelected(item) },
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight(),
                model = PokemonSpriteUrlBuilder.build(item.id),
                placeholder = painterResource(R.drawable.ic_pokeball_outlined),
                contentScale = ContentScale.Fit,
                contentDescription = "${item.name} image",
                imageLoader = imgLoader
            )

            Text(
                text = item.name.replaceFirstChar { it.uppercase() },
                modifier = Modifier
                    .padding(all = 12.dp),
                fontSize = 24.sp
            )

            Icon(
                imageVector = Icons.AutoMirrored.Sharp.ArrowForward,
                contentDescription = "go to detail",
                tint = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier.padding(all = 12.dp)
            )
        }
    }

}


@Preview(showBackground = true, heightDp = 400, widthDp = 720)
@Composable
fun DefaultPreview() {
    PokomposeTheme {
        PokemonRefItem(PokemonRefDO.Entity(50, "Bulbasaur")) {}
    }
}
