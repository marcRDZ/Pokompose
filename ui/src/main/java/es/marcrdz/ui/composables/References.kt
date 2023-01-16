/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.ui.R
import es.marcrdz.ui.theme.BlackAlpha
import es.marcrdz.ui.theme.PokeGray
import es.marcrdz.ui.theme.PokeRed
import es.marcrdz.ui.theme.PokomposeTheme
import es.marcrdz.ui.theme.Shapes


@Composable
fun PokemonRefItemList(
    refs: List<PokemonRefDO.Entity>,
    onEndReached: () -> Unit,
    onItemSelected: (PokemonRefDO.Entity) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = BlackAlpha)
    ) {
        refs.takeIf { it.isNotEmpty() }?.let {
            items(count = refs.size) { index ->
                PokemonRefItem(item = refs[index], modifier) {
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
    Row(
        modifier = modifier
            .height(128.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 2.dp)
            .background(
                color = PokeGray,
                shape = Shapes.medium
            )
            .clickable { onItemSelected(item) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        (item as? PokemonRefDO.Entity)?.let {
            Column(
                modifier = modifier
                    .padding(all = 12.dp)
                    .width(128.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GlideImage(
                    imageModel = { it.imgUri },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.FillBounds
                    ),
                    loading = {
                        Icon(
                            painterResource(id = R.drawable.ic_pokeball_outlined),
                            contentDescription = "pokeball icon"
                        )
                    }
                )
            }
        }
        Text(
            text = item.name.replaceFirstChar { it.uppercase() },
            modifier = modifier
                .padding(all = 12.dp),
            color = BlackAlpha
        )
        Icon(
            imageVector = Icons.AutoMirrored.Sharp.ArrowForward,
            contentDescription = "go to detail",
            tint = PokeRed,
            modifier = Modifier.padding(all = 12.dp)
        )
    }
}


@Preview(showBackground = true, heightDp = 400, widthDp = 720)
@Composable
fun DefaultPreview() {
    PokomposeTheme {
        PokemonRefItem( PokemonRefDO.Entity(0, "Bulbasaur")) {}
    }
}
