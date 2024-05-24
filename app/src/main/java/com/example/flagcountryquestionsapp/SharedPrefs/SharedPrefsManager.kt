package com.example.flagcountryquestionsapp.SharedPrefs

import android.content.Context

class SharedPrefsManager(context: Context) {
    private val sharedPrefs = context.getSharedPreferences("shared_prefs",Context.MODE_PRIVATE)

    fun saveString(key:String, value:String){
        sharedPrefs
            .edit()
            .putString(key,value)
            .apply()
    }

    fun getString(key: String, value: String="UserName"):String{
        return sharedPrefs.getString(key,value).toString()

    }

    fun saveInt(key:String,value: Int){
        sharedPrefs
            .edit()
            .putInt(key,value)
            .apply()
    }

    fun getInt(key: String,value: Int=0):Int{
        return sharedPrefs.getInt(key,value)
    }
}