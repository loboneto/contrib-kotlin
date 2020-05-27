package com.example.contribmontano

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MontanoViewModel by lazy {
        ViewModelProvider(this).get(MontanoViewModel::class.java)
    }

    private var repositories = ArrayList<Repository>()
    private val adapter = RepositoryAdapter(repositories)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.requestRepositories()

        setUpRecyclerView()

        viewModel.getRepositories().observe(this, Observer { repositories ->
            repositories.forEach {
                Log.d(TAG, it.name)
            }
            updateRepositories(repositories)
        })
    }

    private fun setUpRecyclerView() {
        recycler_view_repositories.layoutManager = LinearLayoutManager(this)
        recycler_view_repositories.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        recycler_view_repositories.adapter = adapter
    }

    private fun updateRepositories(repositories: List<Repository>) {
        this.repositories.clear()
        this.repositories.addAll(repositories)
        this.adapter.notifyDataSetChanged()
    }

    companion object {
        const val TAG = "MAIN"
    }
}
