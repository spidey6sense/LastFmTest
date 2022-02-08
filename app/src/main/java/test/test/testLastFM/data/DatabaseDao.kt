package test.test.testLastFM.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import test.test.testLastFM.data.entities.ArtistEntity

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtistsList(posts: List<ArtistEntity>)

    @Query("SELECT * FROM artists  ORDER BY id ASC")
    fun getArtists(): Flow<List<ArtistEntity>>

    @Query("DELETE FROM artists")
    suspend fun clearBase()
}