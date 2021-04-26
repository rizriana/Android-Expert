package id.expert.capstoneproject.core.data.source.remote.network

import id.expert.capstoneproject.core.data.source.remote.response.ListMoviesResponse
import id.expert.capstoneproject.core.utils.Constant.Companion.MOVIES_POPULAR
import id.expert.capstoneproject.core.utils.Constant.Companion.STRING_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(MOVIES_POPULAR)
    suspend fun getMovies(
        @Query(STRING_API_KEY) apiKey: String
    ): ListMoviesResponse
}