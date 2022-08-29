package es.marcrdz.ui.feature.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import es.marcrdz.presentation.base.ErrorEvent
import es.marcrdz.presentation.domain.PresentationReference
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.ui.base.BaseView
import es.marcrdz.ui.composables.ReferenceItem
import es.marcrdz.ui.composables.ReferenceItemList
import es.marcrdz.ui.theme.PokomposeTheme
import es.marcrdz.ui.theme.Shapes

@AndroidEntryPoint
class MainActivity : BaseView<MainEvent.UI, MainEvent.Data, MainStateHolder, MainViewModel>,
    ComponentActivity() {

    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStateCollector()
        setContent {
            PokomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ReferenceItem(0, "Android")
                }
            }
        }

    }

    override fun initStateCollector() {
        lifecycleScope.launchWhenCreated {

        }
    }

    override fun processViewState(viewState: MainEvent.Data) {
        //To be removed?
    }

    override fun processErrorState(errorState: ErrorEvent) {
        //To be removed?
        Log.e(MainActivity::class.simpleName, errorState.toString())
    }

}

@Composable
fun ReferenceItemList(values: List<PresentationReference>) {
    Column() {
        values.map { ReferenceItem(id = it.id, name = it.name) }
    }
}

@Composable
fun ReferenceItem(id: Int, name: String) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .padding(horizontal = 10.dp)
            .background(
                color = Color.DarkGray,
                shape = Shapes.medium
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, modifier = Modifier.padding(all = 8.dp), color = Color.LightGray)
        Icon(
            imageVector = Icons.Sharp.ArrowForward,
            contentDescription = "go to detail",
            tint = Color.LightGray,
            modifier = Modifier.padding(all = 8.dp)
        )
    }

}

@Preview(showBackground = true, heightDp = 300, widthDp = 200)
@Composable
fun DefaultPreview() {
    PokomposeTheme {
        ReferenceItem(0, "Android")
    }
}