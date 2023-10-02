package com.example.kotlinpractice4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class UsersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backBtn: Button = view.findViewById(R.id.backBtn)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        getUsersFromDB(recyclerView)

        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getUsersFromDB(recyclerView: RecyclerView) {
        val userDatabase = UserDatabase.getDataBase(requireContext())
        userDatabase.getUserDao().getAllUsers().asLiveData().observe(viewLifecycleOwner) {
            val usersList: List<User> = it
            val usersListAdapter = UsersListAdapter(usersList)
            recyclerView.adapter = usersListAdapter
            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView.layoutManager = layoutManager
        }
    }
}