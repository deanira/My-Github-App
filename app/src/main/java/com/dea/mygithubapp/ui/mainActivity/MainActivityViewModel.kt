package com.dea.mygithubapp.ui.mainActivity

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
class MainActivityViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    val listUser = MutableLiveData<ArrayList<UsersResponseItem>>()

    fun onFetchUsers() {
        viewModelScope.launch {
            var usersResponse: ArrayList<UsersResponseItem> = arrayListOf()
            when (val response = repository.getUsers()) {
                is Resource.Success -> {
                    response.data?.forEach {
                        usersResponse.add(it)
                    }
                    listUser.postValue(usersResponse)
                }
                is Resource.Error -> {

                }
            }
        }
    }
}