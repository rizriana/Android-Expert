package id.expert.capstoneproject.detail

import androidx.lifecycle.ViewModel
import id.expert.capstoneproject.core.domain.model.Movies
import id.expert.capstoneproject.core.domain.usecase.MoviesUseCase

class DetailMoviesViewModel (private val moviesUseCase: MoviesUseCase) : ViewModel() {

    fun setFavoriteItem(movies: Movies, newStatus: Boolean) = moviesUseCase.setFavoriteMovies(movies, newStatus)
}