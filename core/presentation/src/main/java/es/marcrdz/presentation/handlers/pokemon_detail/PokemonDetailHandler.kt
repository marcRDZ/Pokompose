/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.presentation.handlers.pokemon_detail

import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.usecases.FetchPokemonByIdUseCase
import es.marcrdz.domain.usecases.UseCase
import es.marcrdz.presentation.PresentationContract
import es.marcrdz.presentation.domain.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PokemonDetailHandler :
    PresentationContract.EventFlowHandler<PokemonDetailEvent, PokemonDetailData>

class PokemonDetailHandlerImpl @Inject constructor(
    @FetchPokemonByIdUseCase private val fetchPokemonByIdUc: UseCase<@JvmSuppressWildcards Int, @JvmSuppressWildcards PokemonDO>
) : PokemonDetailHandler {

    override suspend fun handleInit(): Flow<UIState<PokemonDetailData>> {
        /*
        * emit(BackgroundState.Loading)
                 fetchPokemonByIdUc(param = viewEvent.event.pokemonRef.id).let { result ->
                     emit(BackgroundState.Idle)
                     result.mapLeft { it.toFailState() }
                         .fold({ emit(it) }) {
                             emit(ViewState(PokemonRefsEvent.Data.PokemonFetched(it)))
                         }

                 }
        * */
        TODO("Not yet implemented")
    }

    override suspend fun handleEvent(event: PokemonDetailEvent): Flow<UIState<PokemonDetailData>> {
        TODO("Not yet implemented")
    }
}