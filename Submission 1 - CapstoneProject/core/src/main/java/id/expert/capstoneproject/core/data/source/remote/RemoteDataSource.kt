package id.expert.capstoneproject.core.data.source.remote

import android.util.Log
import id.expert.capstoneproject.core.BuildConfig.API_KEY
import id.expert.capstoneproject.core.data.source.remote.network.ApiResponse
import id.expert.capstoneproject.core.data.source.remote.network.ApiService
import id.expert.capstoneproject.core.data.source.remote.response.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor(private val apiService: ApiService) {

    suspend fun getAllMovies(): Flow<ApiResponse<List<MoviesResponse>>> {
        return flow {
            try {
                val response = apiService.getMovies(API_KEY)
                val dataArray = response.moviesResponses
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.moviesResponses))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}