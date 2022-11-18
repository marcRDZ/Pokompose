package es.marcrdz.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import es.marcrdz.presentation.base.BackgroundState
import es.marcrdz.presentation.base.Event
import es.marcrdz.presentation.base.FailState
import es.marcrdz.ui.R
import es.marcrdz.ui.base.BaseStateHolder
import es.marcrdz.ui.theme.BlackAlpha
import kotlinx.coroutines.CoroutineScope

@Composable
fun PokeScaffold(
    backgroundState: BackgroundState,
    failState: FailState?,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    val scaffoldState = rememberScaffoldState()

    failState?.let {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Error message",
                actionLabel = "Retry message"
            )
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
        content(it)
        PokeBallLoadingDialog(state = backgroundState)
    }
}

@Composable
fun PokeBallLoadingDialog(state: BackgroundState, modifier: Modifier = Modifier) {
    if (state is BackgroundState.Loading) {
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