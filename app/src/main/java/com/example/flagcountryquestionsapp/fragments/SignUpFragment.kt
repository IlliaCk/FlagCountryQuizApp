package com.example.flagcountryquestionsapp.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flagcountryquestionsapp.R
import com.example.flagcountryquestionsapp.SharedPrefs.PREFS_USERNAME
import com.example.flagcountryquestionsapp.SharedPrefs.SharedPrefsManager
import com.example.flagcountryquestionsapp.databinding.FragmentSignupBinding

class SignUpFragment:Fragment(R.layout.fragment_signup) {
    private lateinit var binding:FragmentSignupBinding
    private lateinit var sharedPrefs: SharedPrefsManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentSignupBinding.bind(view)
        sharedPrefs= SharedPrefsManager(requireContext())

        binding.btnStart.setOnClickListener {
            if (binding.etUserName.text.toString().isBlank()){
                Toast.makeText(requireContext(), "Please enter your name",Toast.LENGTH_SHORT).show()
            }else{
                val userName=binding.etUserName.text.toString()
                sharedPrefs.saveString(PREFS_USERNAME, userName)
                findNavController().navigate(R.id.action_signUpFragment_to_quizzGameFragment)
            }
        }
    }
}