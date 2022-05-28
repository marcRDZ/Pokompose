package es.marcrdz.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.marcrdz.presentation.handlers.main.MainStateHandler
import es.marcrdz.presentation.handlers.main.MainStateHandlerImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class PresentationModule {

    @Binds
    abstract fun bindMainStateHandler(mainStateHandler: MainStateHandlerImpl): MainStateHandler
}