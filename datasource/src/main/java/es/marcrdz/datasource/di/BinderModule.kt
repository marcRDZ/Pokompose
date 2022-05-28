package es.marcrdz.datasource.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.marcrdz.data.DataContract
import es.marcrdz.datasource.sources.PokemonRemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class BinderModule {

    @Binds
    abstract fun bindPokemonRemoteDataSource(datasource: PokemonRemoteDataSource): DataContract.PokemonDataSource

}