/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.presentation.handlers.pokemon_refs

import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.domain.domain.ReferencePageDO
import es.marcrdz.domain.usecases.FetchPokemonByIdUseCase
import es.marcrdz.domain.usecases.FetchPokemonReferencesUseCase
import es.marcrdz.domain.usecases.UseCase
import es.marcrdz.presentation.PresentationContract
import es.marcrdz.presentation.domain.BackgroundState
import es.marcrdz.presentation.domain.FailState
import es.marcrdz.presentation.domain.DataState
import es.marcrdz.presentation.domain.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface PokemonRefsHandler :
    PresentationContract.EventFlowHandler<PokemonRefsEvent, PokemonRefsData>

class PokemonRefsHandlerImpl @Inject constructor(
    @FetchPokemonReferencesUseCase private val fetchPokemonReferencesUC: UseCase<@JvmSuppressWildcards Nothing, @JvmSuppressWildcards ReferencePageDO<PokemonRefDO.Entity>>,
    @FetchPokemonByIdUseCase private val fetchPokemonByIdUc: UseCase<@JvmSuppressWildcards Int, @JvmSuppressWildcards PokemonDO>
) : PokemonRefsHandler {

    override suspend fun handleInit(): Flow<UIState<PokemonRefsData>> = loadReferences()

    override suspend fun handleEvent(event: PokemonRefsEvent): Flow<UIState<PokemonRefsData>> =
        when (event) {
            PokemonRefsEvent.OnEndListReached -> loadReferences()
            is PokemonRefsEvent.OnPokemonSelected -> flow {}
        }

    private fun loadReferences() = flow {
        emit(BackgroundState.Loading)
        fetchPokemonReferencesUC().let { result ->
            emit(BackgroundState.Idle)
            result.fold({ emit(FailState(error = it)) }) {
                emit(DataState(PokemonRefsData(references = it.results)))
            }
        }
    }

}