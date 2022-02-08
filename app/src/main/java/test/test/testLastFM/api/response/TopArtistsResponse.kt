package test.test.testLastFM.api.response

import com.google.gson.annotations.SerializedName


data class Image(
    @field:SerializedName("#text") val text: String,
    @field:SerializedName("size") val size: String
)

data class Artist(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("playcount") val playcount: String,
    @field:SerializedName("listeners") val listeners: String,
    @field:SerializedName("mbid") val mbid: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("streamable") val streamable: String,
    @field:SerializedName("image") val image: ArrayList<Image>
)

data class Attr(
    @field:SerializedName("page") val page: String,
    @field:SerializedName("perPage") val perPage: String,
    @field:SerializedName("totalPages") val totalPages: String,
    @field:SerializedName("total") val total: String
)

data class Artists(
    @field:SerializedName("artist") val artists: ArrayList<Artist>,
    @field:SerializedName("@attr") val attr: Attr
)

data class TopArtistsResponse(
    @field:SerializedName("artists") val root: Artists
)