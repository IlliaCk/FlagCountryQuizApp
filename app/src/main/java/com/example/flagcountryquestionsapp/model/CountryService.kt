package com.example.flagcountryquestionsapp.model

import com.example.flagcountryquestionsapp.R

class CountryService {

    val countryNameFlagList = listOf(
        Country("Argentina",R.drawable.ic_flag_of_argentina),
        Country("Australia",R.drawable.ic_flag_of_australia),
        Country("Belgium",R.drawable.ic_flag_of_belgium),
        Country("Brazil",R.drawable.ic_flag_of_brazil),
        Country("Denmark",R.drawable.ic_flag_of_denmark),
        Country("Fiji",R.drawable.ic_flag_of_fiji),
        Country("Germany",R.drawable.ic_flag_of_germany),
        Country("India",R.drawable.ic_flag_of_india),
        Country("Kuwait",R.drawable.ic_flag_of_kuwait),
        Country("New Zealand",R.drawable.ic_flag_of_new_zealand)
    )

    val countryNameList = listOf("Argentina", "Australia",
        "Armenia", "Austria","Angola","Belarus", "Belize",
        "Brunei", "Brazil","Bahamas", "Belgium",
        "Barbados","Dominica", "Egypt",
        "Denmark", "Ethiopia","Gabon", "France",
        "Fiji", "Finland","Germany", "Georgia",
        "Greece","Ireland", "Iran",
        "Hungary", "India","New Zealand",
        "Tuvalu", "United States of America","Kuwait", "Jordan",
        "Sudan", "Palestine")

    fun listOfQuestions():List<Question>{
        val questionsList= mutableListOf<Question>()

        countryNameFlagList.forEach {
            val setOfOptionsPack = mutableSetOf<String>()
            setOfOptionsPack.add(it.name)

            while (setOfOptionsPack.size<4){
                val anyOption=countryNameList.random()
                setOfOptionsPack.add(anyOption)
            }
            val listOfOptionsPack = setOfOptionsPack.toList().shuffled()

            val question = Question(it.flag,
                listOfOptionsPack[0],
                listOfOptionsPack[1],
                listOfOptionsPack[2],
                listOfOptionsPack[3],
                it.name)

            questionsList.add(question)
        }

        return questionsList
    }
}