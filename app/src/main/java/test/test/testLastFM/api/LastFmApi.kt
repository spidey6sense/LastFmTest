package test.test.testLastFM.api


import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import test.test.testLastFM.BuildConfig
import test.test.testLastFM.api.response.TopArtistsResponse


interface LastFmApi {

    @GET("2.0/")
    suspend fun getArtistsTopList(
        @Query("method") method: String = "chart.gettopartists",
        @Query("api_key") apiKey: String = BuildConfig.LASTFM_API_KEY,
        @Query("format") format: String = "json",
    ): TopArtistsResponse

    companion object {
        private const val BASE_URL = "https://ws.audioscrobbler.com/"
        fun create(): LastFmApi {
            val client = OkHttpClient.Builder()
                .build()
            return Retrofit.Builder()
                .baseUrl(HttpUrl.parse(BASE_URL)!!)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LastFmApi::class.java)
        }
    }


}