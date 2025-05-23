package com.android.apotekku.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.apotekku.app.ApiConfig
import com.android.apotekku.databinding.ActivityRegisterBinding
import com.android.apotekku.helper.SharedPref
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    lateinit var x:SharedPref
    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnRegister.setOnClickListener {
            register()
        }

    }

    fun register() {
        if(binding.etNamaLengkap.text.isEmpty()) {
            binding.etNamaLengkap.error = "Kolom Nama Lengkap Tidak Boleh Kosong"
            binding.etNamaLengkap.requestFocus()
            return
        } else if(binding.etEmail.text.isEmpty()) {
            binding.etEmail.error = "Kolom Email Tidak Boleh Kosong"
            binding.etEmail.requestFocus()
            return
        } else if(binding.etNomor.text.isEmpty()) {
            binding.etNomor.error = "Kolom Nomor Telepon Tidak Boleh Kosong"
            binding.etNomor.requestFocus()
            return
        } else if(binding.etPassword.text.isEmpty()) {
            binding.etPassword.error = "Kolom Password Tidak Boleh Kosong"
            binding.etPassword.requestFocus()
            return
        }

        val namaLengkap = binding.etNamaLengkap.text.toString()
        val email = binding.etEmail.text.toString()
        val nomorTelepon = binding.etNomor.text.toString() // Perhatikan ini belum dipanggil ke API
        val password = binding.etPassword.text.toString()
        ApiConfig.instanceRetrofit.register(namaLengkap, email, password).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                //menangani ketika sukses
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //menangani ketika gagal
            }

        })

    }
}
