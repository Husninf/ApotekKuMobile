package com.android.apotekku.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharedPref(activity: Activity) {
    val login = "login"
    val mypref = "UTAMA_PRF"
    val sp:SharedPreferences

    init {
        sp = activity.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    }
    fun setStatusLogin(status:Boolean){
        sp.edit().putBoolean(login, status).apply()

    }

    fun getStatusLogin():Boolean{
        return sp.getBoolean(login, false)
    }

}