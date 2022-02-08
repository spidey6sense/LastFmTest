package test.test.testLastFM.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import test.test.testLastFM.api.LastFmApi
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideLastFmApi(): LastFmApi {
        return LastFmApi.create()
    }
}