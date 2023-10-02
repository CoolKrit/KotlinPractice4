package com.example.kotlinpractice4

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GetUsersFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_get_users, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView: TextView = view.findViewById(R.id.textView)
        val toUsersButton: Button = view.findViewById(R.id.toUsersBtn)
        val getUsersButton: Button = view.findViewById(R.id.getUsersBtn)
        val userDatabase = UserDatabase.getDataBase(requireContext())

        getUsersButton.setOnClickListener {
            getUsers(userDatabase)
            textView.text = "Receiving users..."
        }

        userDatabase.getUserDao().getAllUsers().asLiveData().observe(viewLifecycleOwner) {
            if (it.size >= 9) {
                getUsersButton.visibility = View.GONE
                textView.text = "Users has been gotten"
                toUsersButton.visibility = View.VISIBLE
            }
        }

        toUsersButton.setOnClickListener {
            findNavController().navigate(R.id.action_getUsersFragment_to_usersFragment)
        }
    }

    private fun getUsers(userDatabase: UserDatabase) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val mainApi = retrofit.create(MainApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            for (i in 1 until 10) {
                val product = mainApi.getUserById(i)
                userDatabase.getUserDao().insertUser(product)
            }
        }
    }
}