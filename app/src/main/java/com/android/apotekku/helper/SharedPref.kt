package com.android.apotekku.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.android.apotekku.model.UserHandler
import com.google.gson.Gson

class SharedPref(activity: Activity) {
    val login = "login"

//    val nama = "nama"
//    val phone = "phone"
//    val email = "email"

    val user = "user"

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
       //fungsi untuk menyimpan data user
    fun setString(key:String, value:String){
        sp.edit().putString(key, value).apply()
    }

    fun getString(key:String):String{
        return sp.getString(key, "")!!
    }

    //menyimpan data user
    //exclusive 1 paket object, tidak one by one
    fun setUser(value: UserHandler){
        //ubah object menjadi string
        val data:String = Gson().toJson(value, UserHandler::class.java)
        sp.edit().putString(user, data).apply()
    }

    //mengambil data user
    fun getUser() : UserHandler?{
        //ubah string menjadi object
        // ?: apakah data null?
        val data:String = sp.getString(user, null) ?: return null
        return Gson().fromJson<UserHandler>(data, UserHandler::class.java)
    }

}