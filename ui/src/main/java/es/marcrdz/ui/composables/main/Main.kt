package es.marcrdz.ui.composables.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import es.marcrdz.presentation.base.BackgroundState
import es.marcrdz.ui.R
import es.marcrdz.ui.composables.ReferenceItemList
import es.marcrdz.ui.feature.main.MainStateHolder
import es.marcrdz.ui.theme.BlackAlpha
import kotlinx.coroutines.launch


@Composable
fun MainContent(stateHolder: MainStateHolder, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    stateHolder.failState.collectAsState(initial = null).value?.let {
        scope.launch {
            scaffoldState.snackbarHostState.showSnackbar("Oops... something went wrong!")
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painterResource(id = R.drawable.ic_pokeball_outlined),
                            contentDescription = "pokeball icon"
                        )
                        Spacer(Modifier.width(16.dp))
                        Text(stringResource(id = R.string.app_name))
                    }

                }
            )
        }
    ) {
        ReferenceItemList(refs = stateHolder.pokemonRefs.collectAsState(initial = emptyList()).value)
        PokeBallLoadingDialog(state = stateHolder.backgroundState.collectAsState(initial = BackgroundState.Loading))
    }
}

@Composable
fun PokeBallLoadingDialog(state: State<BackgroundState>, modifier: Modifier = Modifier) {
    if (state.value is BackgroundState.Loading) {
        Dialog(
            onDismissRequest = { /*TODO*/ },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                modifier = modifier
                    .size(128.dp)
                    .background(BlackAlpha, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}