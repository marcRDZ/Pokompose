/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.presentation.handlers.main

import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.domain.domain.ReferencePageDO
import es.marcrdz.domain.usecases.FetchPokemonByIdUseCase
import es.marcrdz.domain.usecases.FetchPokemonReferencesUseCase
import es.marcrdz.domain.usecases.UseCase
import es.marcrdz.presentation.PresentationContract
import es.marcrdz.presentation.domain.BackgroundState
import es.marcrdz.presentation.domain.FailState
import es.marcrdz.presentation.domain.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface MainEventHandler : PresentationContract.EventFlowHandler<MainEvent, MainData>

class MainEventHandlerImpl @Inject constructor(
    @FetchPokemonReferencesUseCase private val fetchPokemonReferencesUC: UseCase<@JvmSuppressWildcards Nothing, @JvmSuppressWildcards ReferencePageDO<PokemonRefDO.Entity>>,
    @FetchPokemonByIdUseCase private val fetchPokemonByIdUc: UseCase<@JvmSuppressWildcards Int, @JvmSuppressWildcards PokemonDO>
) : MainEventHandler {

    override suspend fun handleEvent(event: MainEvent): Flow<UIState<MainData>> =
        when (event) {
            is MainEvent.PokemonSelected -> flow { }
            is MainEvent.OnChildFail -> flow { emit(FailState(event.error)) }
            is MainEvent.OnChildWorking -> flow {
                emit(if (event.isLoading) BackgroundState.Loading else BackgroundState.Idle)
            }
        }
}