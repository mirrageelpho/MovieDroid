package com.example.moviedroid.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedroid.databinding.RowMovieBinding
import com.example.moviedroid.services.listener.MovieListener
import com.example.moviedroid.services.model.MovieModel
import com.example.moviedroid.view.viewholder.HomeViewHolder

class HomeAdapter: RecyclerView.Adapter<HomeViewHolder>() {

    private var mList: List<MovieModel> = arrayListOf()
    private lateinit var mMovieListener: MovieListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindData(mList[position])
        with(mList[position]){
            holder.itemView.setOnClickListener{
                mMovieListener.navigate(id)
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    fun attachListener(listener: MovieListener) {
        mMovieListener = listener
    }


    fun updateList(list: List<MovieModel>){
        mList = list
        notifyDataSetChanged()
    }

}

