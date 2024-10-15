/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import es.marcrdz.domain.domain.PokemonAbilityDO
import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.domain.domain.PokemonSpriteUrlBuilder
import es.marcrdz.ui.R

@Composable
fun PokemonDetail(
    pokemon: PokemonDO
) {
    val imgLoader = LocalContext.current.imageLoader

    Row {
        AsyncImage(
            modifier = Modifier,
            model = PokemonSpriteUrlBuilder
                .officialArtwork()
                .build(pokemon.id),
            placeholder = painterResource(R.drawable.ic_pokeball_outlined),
            contentScale = ContentScale.Fit,
            contentDescription = "${pokemon.name} image",
            imageLoader = imgLoader
        )
        Column {
            SimpleLabelledContentView(
                label = stringResource(id = R.string.type),
                content = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() },
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp)

            )
            SimpleLabelledContentView(
                label = stringResource(id = R.string.height),
                content = pokemon.height.toString(),
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp)

            )
            SimpleLabelledContentView(
                label = stringResource(id = R.string.weight),
                content = pokemon.weight.toString(),
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp)

            )
            SimpleLabelledContentView(
                label = stringResource(id = R.string.base_xp),
                content = pokemon.baseExperience.toString(),
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp)

            )
            SimpleLabelledContentView(
                label = stringResource(id = R.string.abilities),
                content = pokemon.abilities.joinToString { it.ability.name },
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp)

            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PokemonDetailPreview() {
    PokemonDetail(
        pokemon = PokemonDO(
            id = 1,
            name = "Bulbasaur",
            height = 7,
            weight = 69,
            baseExperience = 64,
            isDefault = true,
            order = 1,
            species = PokemonRefDO.Species(
                id = 1,
                name = "Bulbasaur"
            ),
            abilities = listOf(
                PokemonAbilityDO(
                    isHidden = false,
                    slot = 1,
                    ability = PokemonRefDO.Ability(
                        id = 34,
                        name = "overgrow"
                    )
                ),
                PokemonAbilityDO(
                    isHidden = false,
                    slot = 1,
                    ability = PokemonRefDO.Ability(
                        id = 34,
                        name = "chlorophyll"
                    )
                )
            ),
            forms = listOf(),
            gameIndices = listOf(),
            heldItems = listOf(),
            moves = listOf(),
            stats = listOf(),
            types = listOf()
        )
    )
}