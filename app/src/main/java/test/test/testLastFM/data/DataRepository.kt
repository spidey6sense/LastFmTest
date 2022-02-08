package test.test.testLastFM.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import test.test.testLastFM.api.LastFmApi
import test.test.testLastFM.api.response.TopArtistsResponse
import test.test.testLastFM.data.entities.ArtistEntity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataRepository @Inject constructor(
    private val mLastFmApi: LastFmApi,
    private val mDatabaseDao: DatabaseDao
) {

    fun getArtists() = mDatabaseDao.getArtists()

    suspend fun fetchData() =
        withContext(Dispatchers.IO) {
            try {
                val response = mLastFmApi.getArtistsTopList()
                val convert = convertResponseToEntity(response)
                mDatabaseDao.clearBase()
                mDatabaseDao.insertArtistsList(convert)
            } catch (exception: Exception) {
                handleError(exception)
            }
        }

    private fun handleError(exception: java.lang.Exception) {
        Log.e("SomeHandleError", exception.toString())
    }

    private fun convertResponseToEntity(response: TopArtistsResponse): List<ArtistEntity> {
        val artists = mutableListOf<ArtistEntity>()
        response.root.artists.map {
            artists.add(
                ArtistEntity(
                    0,
                    it.name,
                    it.image.first { image -> image.size == "medium" }.text,
                    it.listeners
                )
            )
        }
        return artists
    }

}