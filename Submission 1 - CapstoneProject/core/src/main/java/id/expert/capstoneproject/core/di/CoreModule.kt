package id.expert.capstoneproject.core.di

import androidx.room.Room
import id.expert.capstoneproject.core.BuildConfig.BASE_URL
import id.expert.capstoneproject.core.data.MoviesRepository
import id.expert.capstoneproject.core.data.source.local.LocalDataSource
import id.expert.capstoneproject.core.data.source.local.room.MoviesDatabase
import id.expert.capstoneproject.core.data.source.remote.RemoteDataSource
import id.expert.capstoneproject.core.data.source.remote.network.ApiService
import id.expert.capstoneproject.core.domain.repository.IMoviesRepository
import id.expert.capstoneproject.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MoviesDatabase>().moviesDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java, "Movies.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMoviesRepository> {
        MoviesRepository(
            get(),
            get(),
            get()
        )
    }
}