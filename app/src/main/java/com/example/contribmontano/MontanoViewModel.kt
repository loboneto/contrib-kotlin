package com.example.contribmontano

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MontanoViewModel : ViewModel() {

    private val service = GithubFactory.githubService()
    private val repositories = MutableLiveData<List<Repository>>()

    fun getRepositories(): LiveData<List<Repository>> = repositories

    fun requestRepositories() {
        GlobalScope.launch(Dispatchers.Main) {
            repositories.value = loadRepositories()
        }
    }

    private suspend fun loadRepositories(): List<Repository> {
        val list = ArrayList<Repository>()
        withContext(Dispatchers.IO) {
            val response = service.getRepositories()
            response.body()?.also { repositories ->
                list.addAll(repositories)
            }
        }
        return list
    }
}
