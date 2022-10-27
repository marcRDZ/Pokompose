package es.marcrdz.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.marcrdz.domain.domain.ReferenceDO
import es.marcrdz.ui.theme.BlackAlpha
import es.marcrdz.ui.theme.PokeGray
import es.marcrdz.ui.theme.PokeRed
import es.marcrdz.ui.theme.Shapes


@Composable
fun ReferenceItemList(
    refs: List<ReferenceDO>,
    onEndReached: suspend () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = BlackAlpha)
    ) {
        refs.takeIf { it.isNotEmpty() }?.let {
            items(count = refs.size) { index ->
                ReferenceItem(item = refs[index], modifier)
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
fun ReferenceItem(item: ReferenceDO, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 2.dp)
            .background(
                color = PokeGray,
                shape = Shapes.medium
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.name, modifier = Modifier.padding(all = 12.dp), color = BlackAlpha)
        Icon(
            imageVector = Icons.Sharp.ArrowForward,
            contentDescription = "go to detail",
            tint = PokeRed,
            modifier = Modifier.padding(all = 12.dp)
        )
    }
}