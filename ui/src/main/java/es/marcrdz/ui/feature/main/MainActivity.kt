package es.marcrdz.ui.feature.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import es.marcrdz.presentation.base.ErrorEvent
import es.marcrdz.presentation.domain.PresentationReference
import es.marcrdz.presentation.handlers.main.MainEvent
import es.marcrdz.ui.R
import es.marcrdz.ui.base.BaseView
import es.marcrdz.ui.composables.ReferenceItem
import es.marcrdz.ui.composables.ReferenceItemList
import es.marcrdz.ui.composables.main.MainContent
import es.marcrdz.ui.theme.BlackAlpha
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokomposeTheme {
        ReferenceItemList(
            refs = mutableStateOf(
                listOf(
                    PresentationReference.Berry(0, "Apple"),
                    PresentationReference.Berry(0, "Strawberry")
                )
            )
        )
    }
}