package com.example.contribmontano

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private val viewModel: MontanoViewModel by lazy {
        ViewModelProvider(this).get(MontanoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.requestRepositories()

        viewModel.getRepositories().observe(this, Observer { repositories ->
            repositories.forEach { repository ->
                Log.d(TAG, repository.name)
            }
        })
    }

    companion object {
        const val TAG = "MAIN"
    }
}
