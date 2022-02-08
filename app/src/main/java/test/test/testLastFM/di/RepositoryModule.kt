package test.test.testLastFM.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import test.test.testLastFM.api.LastFmApi
import test.test.testLastFM.data.DataRepository
import test.test.testLastFM.data.DatabaseDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(databaseDao: DatabaseDao, lastFmApi: LastFmApi): DataRepository {
        return DataRepository(lastFmApi, databaseDao)
    }
}