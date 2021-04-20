package com.dea.mygithubapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dea.mygithubapp.Resource
import com.dea.mygithubapp.data.response.UsersResponseItem
import com.dea.mygithubapp.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchActivityViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    val listUserSearchResult = MutableLiveData<ArrayList<UsersResponseItem>>()

    fun onSearchuser(url: String) {
        viewModelScope.launch {
            var searchUserResponse: ArrayList<UsersResponseItem> = arrayListOf()
            when (val response =
                repository.searchUserByUsername(url)) {
                is Resource.Success -> {
                    response.data?.items?.forEach {
                        searchUserResponse.add(it)
                    }
                    listUserSearchResult.postValue(searchUserResponse)
                }
                is Resource.Error -> {

                }
            }
        }
    }
}