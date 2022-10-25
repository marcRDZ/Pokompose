package es.marcrdz.ui.feature.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import es.marcrdz.domain.domain.ReferenceDO
import es.marcrdz.presentation.base.ErrorEvent
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.ui.base.BaseView
import es.marcrdz.ui.composables.ReferenceItemList
import es.marcrdz.ui.composables.main.MainContent
import es.marcrdz.ui.theme.PokomposeTheme

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
                    MainContent(stateHolder = viewModel.stateHolder)
                }
            }
        }

    }

    override fun initStateCollector() {
        lifecycleScope.launchWhenCreated {
            //To be removed?
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


@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun DefaultPreview() {
    PokomposeTheme {
        ReferenceItemList(
            refs =
            listOf(
                ReferenceDO.Berry(0, "Apple"),
                ReferenceDO.Berry(0, "Strawberry")
            )
        )
    }
}