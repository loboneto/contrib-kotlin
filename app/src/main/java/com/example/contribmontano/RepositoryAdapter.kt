package com.example.contribmontano

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_repository.view.*

class RepositoryAdapter(private val repositories: List<Repository>) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_repository,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindView(repositories[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(repository: Repository) {
            itemView.text_view_repository_name.text = repository.name
        }
    }

    companion object {
        const val TAG = "REPOSITORY_ADAPTER"
    }
}