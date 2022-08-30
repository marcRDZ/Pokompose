package es.marcrdz.ui.composables

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import es.marcrdz.presentation.domain.PresentationReference
import es.marcrdz.ui.theme.BlackAlpha
import es.marcrdz.ui.theme.PokeGray
import es.marcrdz.ui.theme.PokeRed
import es.marcrdz.ui.theme.Shapes


@Composable
fun ReferenceItemList(refs: State<List<PresentationReference>>, modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .wrapContentHeight()
        .verticalScroll(ScrollState(0))
        .background(color = BlackAlpha)) {
        refs.value.map { ReferenceItem(id = it.id, name = it.name, modifier) }
    }
}

@Composable
fun ReferenceItem(id: Int, name: String, modifier: Modifier = Modifier) {
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
        Text(text = name, modifier = Modifier.padding(all = 12.dp), color = BlackAlpha)
        Icon(
            imageVector = Icons.Sharp.ArrowForward,
            contentDescription = "go to detail",
            tint = PokeRed,
            modifier = Modifier.padding(all = 12.dp)
        )
    }
}