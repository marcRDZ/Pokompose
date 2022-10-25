package es.marcrdz.datasource.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.sargunvohra.lib.pokekotlin.client.ClientConfig
import me.sargunvohra.lib.pokekotlin.client.KCPokeApi
import me.sargunvohra.lib.pokekotlin.client.KCPokeApiClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class ProviderModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("${context.packageName}_prefs", Context.MODE_PRIVATE)

    @Provides
    fun provideKCPokeApiClient(): KCPokeApi = KCPokeApiClient(
        ClientConfig(
            okHttpConfig = {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
            }
        )
    )
}