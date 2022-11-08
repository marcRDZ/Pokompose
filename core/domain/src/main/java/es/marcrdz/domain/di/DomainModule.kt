package es.marcrdz.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.marcrdz.domain.domain.PokemonDO
import es.marcrdz.domain.domain.PokemonRefDO
import es.marcrdz.domain.domain.ReferencePageDO
import es.marcrdz.domain.usecases.*

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @FetchPokemonReferencesUseCase
    @Binds
    abstract fun bindFetchPokemonReferencesUc(useCase: FetchPokemonReferencesUc): UseCase<Nothing, ReferencePageDO<PokemonRefDO.Entity>>

    @FetchPokemonByIdUseCase
    @Binds
    abstract fun bindFetchPokemonByIdUc(useCase: FetchPokemonByIdUc): UseCase<Int, PokemonDO>

}
