/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import es.marcrdz.domain.domain.ErrorDO
import es.marcrdz.ui.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeScaffold(
    isLoading: Boolean,
    error: ErrorDO?,
    modifier: Modifier = Modifier,
    onFailure: (ErrorDO) -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val snackBarHostState: SnackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    error?.let { fail ->
        coroutineScope.launch {
            val state = snackBarHostState.showSnackbar(
                message = "Something went wrong!",
                actionLabel = "Retry"
            )

            state.takeIf { it == SnackbarResult.ActionPerformed }?.let { onFailure(fail) }
        }
    }

    Scaffold(
        modifier = modifier,
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
                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    scrolledContainerColor = MaterialTheme.colorScheme.inversePrimary,
                )
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {
        content(it)
        PokeBallLoadingDialog(isLoading = isLoading)
    }
}

@Composable
fun PokeBallLoadingDialog(isLoading: Boolean, modifier: Modifier = Modifier) =
    isLoading.takeIf { it }?.let {
        Dialog(
            onDismissRequest = { /*TODO*/ },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            OutlinedCard(
                modifier = modifier
                    .size(128.dp)
            ) {
                CircularProgressIndicator()
            }
        }
    } ?: Unit
