package id.expert.capstoneproject.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import id.expert.capstoneproject.R
import id.expert.capstoneproject.core.data.Resource
import id.expert.capstoneproject.core.ui.MoviesAdapter
import id.expert.capstoneproject.core.utils.Constant.Companion.EXTRA_MOVIES
import id.expert.capstoneproject.databinding.FragmentMoviesBinding
import id.expert.capstoneproject.detail.DetailMoviesActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val moviesAdapter = MoviesAdapter()
            moviesAdapter.onItemClick = { selectedData ->
                val intentToDetail = Intent(activity, DetailMoviesActivity::class.java)
                intentToDetail.putExtra(EXTRA_MOVIES, selectedData)
                startActivity(intentToDetail)
            }

            moviesViewModel.movies.observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> showShimmer()
                        is Resource.Success -> {
                            hideShimmer()
                            moviesAdapter.setData(movies.data)
                        }
                        is Resource.Error -> {
                            hideShimmer()
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                movies.message ?: getString(R.string.text_error)
                        }
                    }
                }
            })

            with(binding.rvMovies) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    private fun showShimmer() {
        binding.rvMovies.showShimmer()
    }

    private fun hideShimmer() {
        binding.rvMovies.hideShimmer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}