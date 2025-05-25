package com.android.apotekku.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.apotekku.MainActivity
import com.android.apotekku.R
import com.android.apotekku.app.ApiConfig
import com.android.apotekku.databinding.ActivityLoginBinding
import com.android.apotekku.databinding.ActivityMainBinding
import com.android.apotekku.helper.SharedPref
import com.android.apotekku.model.ToastHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var x:SharedPref
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        x = SharedPref(this)

        binding.btnLogin.setOnClickListener {
            login()

        }
    }


    fun login() {
        if(binding.etEmail.text.isEmpty()) {
            binding.etEmail.error = "Kolom Email Tidak Boleh Kosong"
            binding.etEmail.requestFocus()
            return
        } else if(binding.etPassword.text.isEmpty()) {
            binding.etPassword.error = "Kolom Password Tidak Boleh Kosong"
            binding.etPassword.requestFocus()
            return
        }

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val progressBar = binding.pb

        progressBar.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.login(email, password).enqueue(object :
            Callback<ToastHandler> {
            override fun onResponse(call: Call<ToastHandler>, response: Response<ToastHandler>) {
                progressBar.visibility = View.GONE
                val merespon = response.body()!!

                if (merespon.success == 1){
                    //menangani ketika berhasil
                    x.setStatusLogin(true)
                    //send data to sharedprefs
                    x.setUser(merespon.user)
                    //x.setString(x.nama, merespon.user.name)
                    //x.setString(x.phone, merespon.user.phone)
                    //x.setString(x.email, merespon.user.email)

                    val pindah = Intent(this@LoginActivity, MainActivity::class.java)
                    //destroy activity agar tidak bisa kembali ke login activity
                    pindah.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(pindah)
                    finish()
                    Toast.makeText(this@LoginActivity,"Selamat datang "+merespon.user.name, Toast.LENGTH_SHORT).show()

                }else{
                    //menangani ketika gagal
                    Toast.makeText(this@LoginActivity,merespon.message, Toast.LENGTH_SHORT).show()


                }
            }

            override fun onFailure(call: Call<ToastHandler>, t: Throwable) {
                //menangani ketika gagal
                progressBar.visibility = View.GONE
                Toast.makeText(this@LoginActivity,"Error "+t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}
