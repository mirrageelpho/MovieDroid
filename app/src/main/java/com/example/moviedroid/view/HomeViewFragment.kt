package com.example.moviedroid.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedroid.R
import com.example.moviedroid.databinding.FragmentHomeBinding
import com.example.moviedroid.services.listener.MovieListener
import com.example.moviedroid.view.adapter.HomeAdapter
import com.example.moviedroid.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeViewFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val mAdapter = HomeAdapter()
    private val binding get() = _binding!!

    private lateinit var mMovieListener: MovieListener


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_movies)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        mMovieListener = object : MovieListener {
            override fun navigate(id: Int) {
                val intent = Intent(context, ProfileMovieActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("id", id)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }

        subscribeObserver()
        mAdapter.attachListener(mMovieListener)
        homeViewModel.list()

        return root
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mMovieListener)
        homeViewModel.list()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeObserver() {
        homeViewModel.movieList.observe(viewLifecycleOwner) {
            if (it.count() > 0) {
                mAdapter.updateList(it)
            }
        }
    }
}