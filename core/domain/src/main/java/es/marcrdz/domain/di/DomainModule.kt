package es.marcrdz.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.marcrdz.domain.domain.DomainReference
import es.marcrdz.domain.usecases.FetchPokemonReferencesUc
import es.marcrdz.domain.usecases.FetchPokemonReferencesUseCase
import es.marcrdz.domain.usecases.UseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @FetchPokemonReferencesUseCase
    @Binds
    abstract fun bindFetchPokemonReferencesUc(useCase: FetchPokemonReferencesUc): UseCase<Nothing, List<DomainReference.Pokemon>>
}