package com.dea.mygithubapp

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvUsers: RecyclerView
    private lateinit var dataName: Array<String>
    private lateinit var dataUsername: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataFollowers: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataProfilePicture: TypedArray

    private var listUser: ArrayList<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = getString(R.string.find_people)

        rvUsers = findViewById(R.id.rv_list)
        rvUsers.setHasFixedSize(true)

        prepare()
        addItem()

        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(listUser)
        rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                openUsersDetailActivity(data)
            }
        })
    }

    private fun openUsersDetailActivity(data: User) {
        val user = User(
            data.username,
            data.name,
            data.location,
            data.repository,
            data.company,
            data.followers,
            data.following,
            data.avatar
        )
        val intentParcelize = Intent(this, DetailUserActivity::class.java)
        intentParcelize.putExtra(DetailUserActivity.EXTRA_USER, user)
        startActivity(intentParcelize)
    }

    private fun addItem() {
        for (position in dataUsername.indices) {
            val user = User(
                dataUsername[position],
                dataName[position],
                dataLocation[position],
                dataRepository[position].toInt(),
                dataCompany[position],
                dataFollowers[position].toInt(),
                dataFollowing[position].toInt(),
                dataProfilePicture.getResourceId(position, -1)
            )
            listUser.add(user)
        }
    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.name)
        dataUsername = resources.getStringArray(R.array.username)
        dataLocation = resources.getStringArray(R.array.location)
        dataCompany = resources.getStringArray(R.array.company)
        dataFollowers = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
        dataRepository = resources.getStringArray(R.array.repository)
        dataProfilePicture = resources.obtainTypedArray(R.array.avatar)
    }

}