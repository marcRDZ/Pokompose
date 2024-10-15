/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.marcrdz.presentation.handlers.main.MainEventHandler
import es.marcrdz.presentation.handlers.main.MainEventHandlerImpl
import es.marcrdz.presentation.handlers.pokemon.PokemonHandler
import es.marcrdz.presentation.handlers.pokemon.PokemonHandlerImpl
import es.marcrdz.presentation.handlers.poke_refs.PokemonRefsHandler
import es.marcrdz.presentation.handlers.poke_refs.PokemonRefsHandlerImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class PresentationModule {

    @Binds
    abstract fun bindMainStateHandler(handler: MainEventHandlerImpl): MainEventHandler

    @Binds
    abstract fun bindPokemonRefsHandler(handler: PokemonRefsHandlerImpl): PokemonRefsHandler

    @Binds
    abstract fun bindPokemonDetailHandler(handler: PokemonHandlerImpl): PokemonHandler

}