package com.amar.dueslistroom.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.amar.dueslistroom.R
import com.amar.dueslistroom.viewmodel.UserViewModel
import com.amar.dueslistroom.model.User
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.add_btn.setOnClickListener {
            insertDataToDatabase()

        }
        return view
    }

    private fun insertDataToDatabase() {
        val firstName = et_firstName.text.toString()
        val lastName = et_lastName.text.toString()
        val due = et_due.text
        if (inputCheck(firstName, lastName, due)) {
            // Create User Object
            val user = User(0, firstName, lastName, Integer.parseInt(due.toString()))
            // Add data to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "user added", Toast.LENGTH_LONG).show()
            // Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else {
            Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(firstName: String, lastName: String, due: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && due.isEmpty())
    }
}