package com.example.flagcountryquestionsapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flagcountryquestionsapp.R
import com.example.flagcountryquestionsapp.SharedPrefs.PREFS_TOTAL_SCORE
import com.example.flagcountryquestionsapp.SharedPrefs.PREFS_USERNAME
import com.example.flagcountryquestionsapp.SharedPrefs.SharedPrefsManager
import com.example.flagcountryquestionsapp.databinding.FragmentResultBinding

class ResultFragment: Fragment(R.layout.fragment_result) {
    private lateinit var binding: FragmentResultBinding
    private lateinit var sharedPrefs:SharedPrefsManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentResultBinding.bind(view)
        sharedPrefs = SharedPrefsManager(requireContext())

       setUItextViews()

        binding.btnPlayAgain.setOnClickListener {
            findNavController().navigate(R.id.quizzGameFragment)
        }
        binding.btnFinish.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
    }
    private fun setUItextViews(){
        val userName= sharedPrefs.getString(PREFS_USERNAME)
        val totalScore = sharedPrefs.getInt(PREFS_TOTAL_SCORE)

        with(binding){
            tvUserNameCongratulation.text ="Congratulation, $userName!"
            tvScore.text = "Your score is $totalScore out of 10"
        }
    }
}