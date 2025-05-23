package com.android.apotekku.app

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") nama: String,
        @Field("email") email: String,
        //@Field("nomor") nomor: String,
        @Field("password") password: String
    ):Call<ResponseBody>

    @POST("login")
    fun login(
        @Field("name") nama: String,
        @Field("email") email: String,
        //@Field("nomor") nomor: String,
        @Field("password") password: String
    ):Call<ResponseBody>
}