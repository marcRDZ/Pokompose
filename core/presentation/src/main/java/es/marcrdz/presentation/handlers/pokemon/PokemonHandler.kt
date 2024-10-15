/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.presentation.handlers.pokemon

import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.usecases.FetchPokemonByIdUseCase
import es.marcrdz.domain.usecases.UseCase
import es.marcrdz.presentation.PresentationContract
import es.marcrdz.presentation.domain.BackgroundState
import es.marcrdz.presentation.domain.DataState
import es.marcrdz.presentation.domain.FailState
import es.marcrdz.presentation.domain.UIState
import es.marcrdz.presentation.handlers.poke_refs.PokemonRefsData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface PokemonHandler :
    PresentationContract.EventFlowHandler<PokemonEvent, PokemonData>

class PokemonHandlerImpl @Inject constructor(
    @FetchPokemonByIdUseCase private val fetchPokemonByIdUc: UseCase<@JvmSuppressWildcards Int, @JvmSuppressWildcards PokemonDO>
) : PokemonHandler {

    override suspend fun handleEvent(event: PokemonEvent): Flow<UIState<PokemonData>> =
        when (event) {
            is PokemonEvent.OnInit -> flow {
                emit(BackgroundState.Loading)
                fetchPokemonByIdUc(param = event.id).let { result ->
                    emit(BackgroundState.Idle)
                    emit(
                        result.fold(
                            ifLeft = { FailState(error = it) },
                            ifRight = { DataState(data = PokemonData(pokemon = it)) }
                        )
                    )

                }
            }
        }
}