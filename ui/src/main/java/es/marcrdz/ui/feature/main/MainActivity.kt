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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import es.marcrdz.presentation.base.ErrorReport
import es.marcrdz.presentation.base.UserEvent
import es.marcrdz.presentation.base.ViewState
import es.marcrdz.presentation.domain.PresentationReference
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.presentation.handlers.main.MainReport
import es.marcrdz.ui.base.BaseView
import es.marcrdz.ui.theme.PokomposeTheme
import es.marcrdz.ui.theme.Shapes
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : BaseView<MainEvent, MainReport>, ComponentActivity() {

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

    override fun onStart() {
        super.onStart()
        viewModel.processViewEvent(UserEvent(MainEvent.ListEndReached))
    }

    override fun initStateCollector() {
        lifecycleScope.launchWhenCreated {
            //viewModel.stateHolder.failState.collectAsState(initial = "")

/*            viewModel.state.collect {
                when (it) {
                    is ViewState.Fail -> processErrorState(it.error)
                    ViewState.Idle -> {}
                    ViewState.Loading -> {}
                    is ViewState.StateChange -> processViewState(it.state)
                }
            }*/
        }
    }

    override fun processViewState(viewState: MainReport) {
        when (viewState) {
            is MainReport.PokemonReferencesFetched -> {
                Log.d("pokemons fetched:", "${viewState.references.size}")
            }
        }
    }

    override fun processErrorState(errorState: ErrorReport) {
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