package com.example.flagcountryquestionsapp.fragments

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flagcountryquestionsapp.R
import com.example.flagcountryquestionsapp.SharedPrefs.PREFS_TOTAL_SCORE
import com.example.flagcountryquestionsapp.SharedPrefs.SharedPrefsManager
import com.example.flagcountryquestionsapp.databinding.FragmentQuizzGameBinding
import com.example.flagcountryquestionsapp.model.CountryService

class QuizzGameFragment: Fragment(R.layout.fragment_quizz_game), View.OnClickListener {
    //private val TAG ="MyTAG"

    private lateinit var binding: FragmentQuizzGameBinding
    private lateinit var sharedPrefs:SharedPrefsManager


    private val countryService=CountryService()

    private var mQuestionsList = countryService.listOfQuestions()
    private var mCurrentPosition = 1
    private var mCorrectAnswers = 0
    private var mSelectedValue = "NO_SELECTED_OPTION"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentQuizzGameBinding.bind(view)
        sharedPrefs = SharedPrefsManager(requireContext())

        setQuestion()

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)

        binding.buttonCheckVariant.setOnClickListener(this)
    }

    private fun setQuestion(){
        val question= mQuestionsList[mCurrentPosition -1]

        defaultOptionView()

        if (mCurrentPosition == mQuestionsList.size){
            binding.buttonCheckVariant.text="FINISH"
        }else{
            binding.buttonCheckVariant.text="SUBMIT"
        }

        with(binding){
            imageView.setImageResource(question.flagImage)
            progressBar.progress=1
            tvProgressBar.text = "$mCurrentPosition/${binding.progressBar.max}"
            tvOptionOne.text=question.optionOne
            tvOptionTwo.text=question.optionTwo
            tvOptionThree.text=question.optionThree
            tvOptionFour.text=question.optionFour

        }


    }
    private fun defaultOptionView(){
        val options = ArrayList<TextView>()

        options.add(0,binding.tvOptionOne)
        options.add(1,binding.tvOptionTwo)
        options.add(2,binding.tvOptionThree)
        options.add(3,binding.tvOptionFour)


        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background= ContextCompat.getDrawable(
                requireContext(),
                R.drawable.default_option_border_bg)
        }
    }
    private fun selectedOptionView(tv:TextView, selectedValue:String){
        defaultOptionView()
        mSelectedValue = selectedValue

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(
            requireContext(),
            R.drawable.selected_option_border_bg)
    }

    override fun onClick(view: View?) {
        val tvOption1 = binding.tvOptionOne
        val tvOption2 = binding.tvOptionTwo
        val tvOption3 = binding.tvOptionThree
        val tvOption4 = binding.tvOptionFour

        when(view?.id){
            tvOption1.id -> {
                selectedOptionView(tvOption1,tvOption1.text.toString())
            }
            tvOption2.id -> {
                selectedOptionView(tvOption2,tvOption2.text.toString())
            }
            tvOption3.id -> {
                selectedOptionView(tvOption3,tvOption3.text.toString())
            }
            tvOption4.id -> {
                selectedOptionView(tvOption4,tvOption4.text.toString())
            }
            binding.buttonCheckVariant.id -> {
                if (mSelectedValue=="NO_SELECTED_OPTION"){
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionsList.size ->{
                            setQuestion()
                        }else ->{
                        sharedPrefs.saveInt(PREFS_TOTAL_SCORE, mCorrectAnswers)
                        findNavController().navigate(R.id.action_quizzGameFragment_to_resultFragment)
                    }
                    }
                }else{
                    val question= mQuestionsList[mCurrentPosition-1]

                    val options = ArrayList<TextView>()
                    options.add(0,binding.tvOptionOne)
                    options.add(1,binding.tvOptionTwo)
                    options.add(2,binding.tvOptionThree)
                    options.add(3,binding.tvOptionFour)

                    if (question.correctAnswer != mSelectedValue){
                        val selectedTextView = findTextViewByValue(mSelectedValue, options)
                        answerView(selectedTextView,R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    val correctAnswerTextView = findTextViewByValue(question.correctAnswer, options)
                    answerView(correctAnswerTextView,R.drawable.correct_option_border_bg)

                    if(mCurrentPosition==mQuestionsList.size){
                        binding.buttonCheckVariant.text="FINISH"
                    }else{
                        binding.buttonCheckVariant.text="GO TO NEXT QUESTION"
                    }
                    mSelectedValue = "NO_SELECTED_OPTION"
                }
            }
        }
    }
    private fun findTextViewByValue(value:String, listOfOptions:List<TextView>):TextView{
        var matchingTextView:TextView?=null
        listOfOptions.forEach {
            if (it.text.toString() == value){
                matchingTextView = it
            }
        }
        return matchingTextView!!
    }
    private fun answerView(tv:TextView,drawableView: Int){

        val tvOption1 = binding.tvOptionOne
        val tvOption2 = binding.tvOptionTwo
        val tvOption3 = binding.tvOptionThree
        val tvOption4 = binding.tvOptionFour

        when(tv){
            tvOption1 -> {
                tvOption1.background = ContextCompat.getDrawable(requireContext(),drawableView)
            }
            tvOption2 -> {
                tvOption2.background = ContextCompat.getDrawable(requireContext(),drawableView)
            }
            tvOption3 -> {
                tvOption3.background = ContextCompat.getDrawable(requireContext(),drawableView)
            }
            tvOption4 -> {
                tvOption4.background = ContextCompat.getDrawable(requireContext(),drawableView)
            }
        }
    }



}