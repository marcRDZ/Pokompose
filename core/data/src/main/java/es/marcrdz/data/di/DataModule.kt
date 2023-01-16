/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.marcrdz.data.repositories.PokemonRepository
import es.marcrdz.domain.DomainContract

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindUserRepository(repository: PokemonRepository): DomainContract.PokemonRepository
}