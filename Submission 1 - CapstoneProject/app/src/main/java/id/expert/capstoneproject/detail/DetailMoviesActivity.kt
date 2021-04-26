package id.expert.capstoneproject.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import id.expert.capstoneproject.BuildConfig.IMAGE_URL
import id.expert.capstoneproject.R
import id.expert.capstoneproject.core.domain.model.Movies
import id.expert.capstoneproject.core.utils.Constant.Companion.DATE_CURRENT_FORMAT
import id.expert.capstoneproject.core.utils.Constant.Companion.DATE_REQUIRED_FORMAT
import id.expert.capstoneproject.core.utils.Constant.Companion.EXTRA_MOVIES
import id.expert.capstoneproject.core.utils.Helper.changeDateFormat
import id.expert.capstoneproject.core.utils.Helper.setGlide
import id.expert.capstoneproject.core.utils.Helper.setGlideWithRadius
import id.expert.capstoneproject.databinding.ActivityDetailMoviesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMoviesBinding
    private val detailMoviesViewModel: DetailMoviesViewModel by viewModel()

    private var newState: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setSupportActionBar(binding.toolbar)

        val detailMovies = intent.getParcelableExtra<Movies>(EXTRA_MOVIES)
        showDetailMovies(detailMovies)
    }

    private fun showDetailMovies(detailMovies: Movies?) {
        detailMovies?.let {
            binding.ivBack.setOnClickListener {
                onBackPressed()
            }
            supportActionBar?.title = null
            setGlide(
                this@DetailMoviesActivity,
                "${IMAGE_URL}${detailMovies.backdropPath}",
                binding.content.ivBackdrop
            )
            setGlideWithRadius(
                this@DetailMoviesActivity,
                "${IMAGE_URL}${detailMovies.posterPath}",
                binding.content.ivPoster
            )

            val releaseDate = changeDateFormat(
                DATE_CURRENT_FORMAT,
                DATE_REQUIRED_FORMAT,
                detailMovies.releaseDate
            )

            binding.content.tvTitle.text = detailMovies.title
            binding.content.tvReleaseYear.text = releaseDate
            binding.content.tvLanguage.text = detailMovies.originalLanguage
            binding.content.ratingBar.rating = detailMovies.voteAverage / 2
            binding.content.tvRating.text = detailMovies.voteAverage.toString()
            binding.content.tvOverview.text = detailMovies.overview
            binding.content.tvVoteCount.text =
                getString(R.string.vote_count, detailMovies.voteCount.toString())

            var statusFavorite = detailMovies.isFavorite
            setStatusFavorite(statusFavorite)
            binding.content.fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMoviesViewModel.setFavoriteItem(detailMovies, statusFavorite)
                setStatusFavorite(statusFavorite)
                if (newState) {
                    showSnackBar(getString(R.string.add_to_favorite, detailMovies.title))
                } else {
                    showSnackBar(getString(R.string.remove_from_favorite, detailMovies.title))
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        binding.content.fabFavorite.setImageDrawable(
            if (statusFavorite) {
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_solid
                )
            } else {
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            }
        )

        newState = statusFavorite
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.content.contentDetailMain,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}