package es.marcrdz.ui.feature.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import es.marcrdz.presentation.base.ViewEvent
import es.marcrdz.presentation.base.ViewState
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.presentation.handlers.main.MainState
import es.marcrdz.ui.base.BaseView
import es.marcrdz.ui.theme.PokomposeTheme
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : BaseView<MainEvent, MainState>, ComponentActivity() {

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
                    Greeting("Android")
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        viewModel.processViewEvent(ViewEvent.Lifecycle.OnStart)
    }

    override fun initStateCollector() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                when (it) {
                    is ViewState.Fail<*> -> {}
                    ViewState.Idle -> {}
                    ViewState.Loading -> {}
                    is ViewState.StateChange -> processViewState(it.state)
                }
            }
        }
    }

    override fun processViewState(viewState: MainState) {
        when(viewState) {
            is MainState.PokemonReferencesFetched -> {
                Log.d("pokemons fetched:", "${viewState.references.size}")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokomposeTheme {
        Greeting("Android")
    }
}